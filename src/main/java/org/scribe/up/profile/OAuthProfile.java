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
package org.scribe.up.profile;

/**
 * This interface is the contract an OAuth profile must follow.
 * 
 * @author Jerome Leleu
 * @since 1.3.0
 */
public interface OAuthProfile {
    
    public void setAccessToken(final String accessToken);
    
    public String getAccessToken();
}
