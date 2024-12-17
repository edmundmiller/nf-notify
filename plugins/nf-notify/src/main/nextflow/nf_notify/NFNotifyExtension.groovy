/*
 * MIT License
 *
 * Copyright (c) 2024 Edmund Miller
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice (including the next
 * paragraph) shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package nextflow.nf_notify

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import nextflow.Session
import nextflow.plugin.extension.PluginExtensionPoint

/**
 * Define your extension.
 *
 * @author Edmund Miller
 */
@Slf4j
@CompileStatic
class NFNotifyExtension extends PluginExtensionPoint {

    /**
     * A session holds information about current nextflow execution.
     */
    protected Session session

    /**
     * A custom configuration extracted from the nf_notify scope.
     */
    protected NFNotifyConfig config

    /**
     * An extension that is automatically initialized by nextflow when the session is ready.
     *
     * @param session A nextflow session instance.
     */
    @Override
    protected void init(final Session session) {
        this.session = session
        this.config = new NFNotifyConfig(session.config?.navigate('nf_notify') as Map)
    }

}
