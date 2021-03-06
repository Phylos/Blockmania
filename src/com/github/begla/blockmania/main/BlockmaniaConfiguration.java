/*
 * Copyright 2011 Benjamin Glatzel <benjamin.glatzel@me.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.begla.blockmania.main;

import groovy.util.ConfigObject;
import groovy.util.ConfigSlurper;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;

/**
 * Blockmania's global settings.
 *
 * @author Benjamin Glatzel <benjamin.glatzel@me.com>
 */
public final class BlockmaniaConfiguration {

    private static final String DEFAULT_CONFIG_PATH = "groovy/config/Default.groovy";
    private static BlockmaniaConfiguration _instance;

    private Map _config;

    public static BlockmaniaConfiguration getInstance() {
        if (_instance == null)
            _instance = new BlockmaniaConfiguration();

        return _instance;
    }

    private BlockmaniaConfiguration() {
        loadConfigEnvironment(null);
    }

    public void loadConfigEnvironment(String environment) {
        ConfigObject config = null;

        try {

            if (environment != null)
                config = new ConfigSlurper(environment).parse(new File(DEFAULT_CONFIG_PATH).toURI().toURL());
            else
                config = new ConfigSlurper().parse(new File(DEFAULT_CONFIG_PATH).toURI().toURL());

        } catch (IOException e) {
            Blockmania.getInstance().getLogger().log(Level.SEVERE, e.toString(), e);
        }

        if (config != null)
            _config = config.flatten();
    }

    public Map getConfig() {
        return _config;
    }
}
