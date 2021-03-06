/*
  Copyright 2012 Jerome Leleu

   Licensed under the Apache License, Version 2.0 (the \"License\");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an \"AS IS\" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package org.scribe.up.test.provider.impl;

import org.scribe.up.profile.twitter.TwitterProfile;
import org.scribe.up.provider.impl.TwitterProvider;

/**
 * This class is the Twitter provider for benching.
 * 
 * @author Jerome Leleu
 * @since 1.2.0
 */
public class BenchTwitterProvider extends TwitterProvider {
    
    public TwitterProfile createProfile(final String body) {
        return (TwitterProfile) extractUserProfile(body);
    }
}
