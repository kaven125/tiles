/*
 * $Id$
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.tiles.freemarker.template;

import java.io.IOException;
import java.util.Map;

import org.apache.tiles.freemarker.FreemarkerModelBody;
import org.apache.tiles.freemarker.context.FreeMarkerUtil;
import org.apache.tiles.request.Request;
import org.apache.tiles.request.freemarker.FreemarkerRequest;
import org.apache.tiles.request.freemarker.FreemarkerRequestUtil;
import org.apache.tiles.template.AddAttributeModel;
import org.apache.tiles.template.body.ModelBody;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateModel;

/**
 * Wraps {@link AddAttributeModel} to be used in FreeMarker. For the list of
 * parameters, see {@link AddAttributeModel#start(Request)} and
 * {@link AddAttributeModel#end(Object, String, String, String, String, Request)}.
 *
 * @version $Rev$ $Date$
 * @since 2.2.0
 */
public class AddAttributeFMModel implements TemplateDirectiveModel {

    /**
     * The template model.
     */
    private AddAttributeModel model;

    /**
     * Constructor.
     *
     * @param model The template model.
     * @since 2.2.0
     */
    public AddAttributeFMModel(AddAttributeModel model) {
        this.model = model;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
            TemplateDirectiveBody body) throws IOException {
        Request request = FreemarkerRequest
                .createServletFreemarkerRequest(FreemarkerRequestUtil
                        .getApplicationContext(env), env);
        ModelBody modelBody = new FreemarkerModelBody(env.getOut(), body);
        Map<String, TemplateModel> parms = params;
        model.execute(FreeMarkerUtil.getAsObject(parms.get("value")),
                FreeMarkerUtil.getAsString(parms.get("expression")),
                FreeMarkerUtil.getAsString(parms.get("role")), FreeMarkerUtil
                        .getAsString(parms.get("type")), request, modelBody);
    }
}
