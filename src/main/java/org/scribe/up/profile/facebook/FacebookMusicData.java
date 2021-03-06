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
package org.scribe.up.profile.facebook;

import org.scribe.up.profile.JsonObject;
import org.scribe.up.profile.converter.Converters;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * This class represents a Facebook music data : song, musician or radio_station.
 * 
 * @author Jerome Leleu
 * @since 1.2.0
 */
public final class FacebookMusicData extends JsonObject {
    
    private static final long serialVersionUID = 7221203529595022102L;
    
    private String id;
    
    private String url;
    
    private String type;
    
    private String title;
    
    @Override
    protected void buildFromJson(final JsonNode json) {
        this.id = Converters.stringConverter.convertFromJson(json, "id");
        this.url = Converters.stringConverter.convertFromJson(json, "url");
        this.type = Converters.stringConverter.convertFromJson(json, "type");
        this.title = Converters.stringConverter.convertFromJson(json, "title");
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public String getType() {
        return this.type;
    }
    
    public String getTitle() {
        return this.title;
    }
}
