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

import org.scribe.model.OAuthConfig;
import org.scribe.model.SignatureType;
import org.scribe.up.addon_to_scribe.GitHubApi;
import org.scribe.up.addon_to_scribe.ProxyOAuth20ServiceImpl;
import org.scribe.up.profile.OAuthAttributesDefinitions;
import org.scribe.up.profile.JsonHelper;
import org.scribe.up.profile.UserProfile;
import org.scribe.up.profile.github.GitHubProfile;
import org.scribe.up.provider.BaseOAuth20Provider;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * This class is the OAuth provider to authenticate user in GitHub.
 * <p />
 * It returns a {@link org.scribe.up.profile.github.GitHubProfile}.
 * <p />
 * More information at http://developer.github.com/v3/users/
 * 
 * @see org.scribe.up.profile.github.GitHubProfile
 * @author Jerome Leleu
 * @since 1.0.0
 */
public class GitHubProvider extends BaseOAuth20Provider {
    
    @Override
    protected GitHubProvider newProvider() {
        return new GitHubProvider();
    }
    
    @Override
    protected void internalInit() {
        this.service = new ProxyOAuth20ServiceImpl(new GitHubApi(),
                                                   new OAuthConfig(this.key, this.secret, this.callbackUrl,
                                                                   SignatureType.Header, "user", null), this.proxyHost,
                                                   this.proxyPort);
    }
    
    @Override
    protected String getProfileUrl() {
        return "https://api.github.com/user";
    }
    
    @Override
    protected UserProfile extractUserProfile(final String body) {
        final GitHubProfile profile = new GitHubProfile();
        final JsonNode json = JsonHelper.getFirstNode(body);
        if (json != null) {
            profile.setId(JsonHelper.get(json, "id"));
            for (final String attribute : OAuthAttributesDefinitions.githubDefinition.getAllAttributes()) {
                profile.addAttribute(attribute, JsonHelper.get(json, attribute));
            }
        }
        return profile;
    }
}
