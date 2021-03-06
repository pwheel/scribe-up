/*
  Copyright 2012 Jerome Leleu

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package org.scribe.up.provider.impl;

import org.apache.commons.lang3.StringUtils;
import org.scribe.builder.api.YahooApi;
import org.scribe.model.OAuthConfig;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.scribe.up.addon_to_scribe.ProxyOAuth10aServiceImpl;
import org.scribe.up.profile.OAuthAttributesDefinitions;
import org.scribe.up.profile.JsonHelper;
import org.scribe.up.profile.UserProfile;
import org.scribe.up.profile.yahoo.YahooProfile;
import org.scribe.up.provider.BaseOAuth10Provider;
import org.scribe.up.provider.exception.HttpException;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * This class is the OAuth provider to authenticate user in Yahoo.
 * <p />
 * It returns a {@link org.scribe.up.profile.yahoo.YahooProfile}.
 * <p />
 * More information at http://developer.yahoo.com/social/rest_api_guide/extended-profile-resource.html
 * 
 * @see org.scribe.up.profile.yahoo.YahooProfile
 * @author Jerome Leleu
 * @since 1.0.0
 */
public class YahooProvider extends BaseOAuth10Provider {
    
    @Override
    protected YahooProvider newProvider() {
        return new YahooProvider();
    }
    
    @Override
    protected void internalInit() {
        this.service = new ProxyOAuth10aServiceImpl(new YahooApi(), new OAuthConfig(this.key, this.secret,
                                                                                    this.callbackUrl,
                                                                                    SignatureType.Header, null, null),
                                                    this.proxyHost, this.proxyPort);
    }
    
    @Override
    protected String getProfileUrl() {
        return "http://social.yahooapis.com/v1/me/guid?format=xml";
    }
    
    @Override
    protected UserProfile retrieveUserProfile(final Token accessToken) throws HttpException {
        // get the guid : http://developer.yahoo.com/social/rest_api_guide/introspective-guid-resource.html
        String body = sendRequestForData(accessToken, getProfileUrl());
        if (body == null) {
            return null;
        }
        final String guid = StringUtils.substringBetween(body, "<value>", "</value>");
        logger.debug("guid : {}", guid);
        // then the profile with the guid
        if (StringUtils.isNotBlank(guid)) {
            body = sendRequestForData(accessToken, "http://social.yahooapis.com/v1/user/" + guid
                                                   + "/profile?format=json");
            if (body == null) {
                return null;
            }
        }
        final UserProfile profile = extractUserProfile(body);
        addAccessTokenToProfile(profile, accessToken);
        return profile;
    }
    
    @Override
    protected UserProfile extractUserProfile(final String body) {
        final YahooProfile profile = new YahooProfile();
        JsonNode json = JsonHelper.getFirstNode(body);
        if (json != null) {
            json = json.get("profile");
            if (json != null) {
                profile.setId(JsonHelper.get(json, "guid"));
                for (final String attribute : OAuthAttributesDefinitions.yahooDefinition.getAllAttributes()) {
                    profile.addAttribute(attribute, JsonHelper.get(json, attribute));
                }
            }
        }
        return profile;
    }
}
