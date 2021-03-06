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
package org.scribe.up.profile.google2;

import java.util.Date;
import java.util.Locale;

import org.scribe.up.profile.AttributesDefinition;
import org.scribe.up.profile.BaseOAuthProfile;
import org.scribe.up.profile.CommonProfile;
import org.scribe.up.profile.Gender;
import org.scribe.up.profile.OAuthAttributesDefinitions;

/**
 * This class is the user profile for Google (using OAuth protocol version 2) with appropriate getters.<br />
 * It is returned by the {@link org.scribe.up.provider.impl.Google2Provider}.
 * <p />
 * <table border="1" cellspacing="2px">
 * <tr>
 * <th>Method :</th>
 * <th>From the JSON profile response :</th>
 * </tr>
 * <tr>
 * <th colspan="2">The attributes of the {@link org.scribe.up.profile.CommonProfile}</th>
 * </tr>
 * <tr>
 * <td>String getEmail()</td>
 * <td>the <i>email</i> attribute</td>
 * </tr>
 * <tr>
 * <td>String getFirstName()</td>
 * <td>the <i>given_name</i> attribute</td>
 * </tr>
 * <tr>
 * <td>String getFamilyName()</td>
 * <td>the <i>family_name</i> attribute</td>
 * </tr>
 * <tr>
 * <td>String getDisplayName()</td>
 * <td>the <i>name</i> attribute</td>
 * </tr>
 * <tr>
 * <td>String getUsername()</td>
 * <td>null</td>
 * </tr>
 * <tr>
 * <td>Gender getGender()</td>
 * <td>the <i>gender</i> attribute</td>
 * </tr>
 * <tr>
 * <td>Locale getLocale()</td>
 * <td>the <i>locale</i> attribute</td>
 * </tr>
 * <tr>
 * <td>String getPictureUrl()</td>
 * <td>the <i>picture</i> attribute</td>
 * </tr>
 * <tr>
 * <td>String getProfileUrl()</td>
 * <td>the <i>link</i> attribute</td>
 * </tr>
 * <tr>
 * <td>String getLocation()</td>
 * <td>null</td>
 * </tr>
 * <tr>
 * <th colspan="2">More specific attributes</th>
 * </tr>
 * <tr>
 * <td>Boolean getVerifiedEmail()</td>
 * <td>the <i>verified_email</i> attribute</td>
 * </tr>
 * <tr>
 * <td>Date getBirthday()</td>
 * <td>the <i>birthday</i> attribute</td>
 * </tr>
 * </table>
 * 
 * @see org.scribe.up.provider.impl.Google2Provider
 * @author Jerome Leleu
 * @since 1.2.0
 */
public class Google2Profile extends BaseOAuthProfile implements CommonProfile {
    
    private static final long serialVersionUID = -548478189554708977L;
    
    @Override
    protected AttributesDefinition getAttributesDefinition() {
        return OAuthAttributesDefinitions.google2Definition;
    }
    
    public String getEmail() {
        return (String) get(Google2AttributesDefinition.EMAIL);
    }
    
    public String getFirstName() {
        return (String) get(Google2AttributesDefinition.GIVEN_NAME);
    }
    
    public String getFamilyName() {
        return (String) get(Google2AttributesDefinition.FAMILY_NAME);
    }
    
    public String getDisplayName() {
        return (String) get(Google2AttributesDefinition.NAME);
    }
    
    public String getUsername() {
        return null;
    }
    
    public Gender getGender() {
        return (Gender) get(Google2AttributesDefinition.GENDER);
    }
    
    public Locale getLocale() {
        return (Locale) get(Google2AttributesDefinition.LOCALE);
    }
    
    public String getPictureUrl() {
        return (String) get(Google2AttributesDefinition.PICTURE);
    }
    
    public String getProfileUrl() {
        return (String) get(Google2AttributesDefinition.LINK);
    }
    
    public String getLocation() {
        return null;
    }
    
    public Boolean getVerifiedEmail() {
        return (Boolean) get(Google2AttributesDefinition.VERIFIED_EMAIL);
    }
    
    public Date getBirthday() {
        return (Date) get(Google2AttributesDefinition.BIRTHDAY);
    }
}
