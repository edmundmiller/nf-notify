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

import groovy.transform.PackageScope
import groovy.transform.CompileStatic

/**
 * Define the plugin configuration values.
 *
 * The configuration values can be extracted from the map and will be stored as
 * on the instance.
 *
 * Annotating the class with `@PackageScope` restricts access to its attributes and
 * methods to other members of the package.
 *
 * TODO: Describe the configuration of your actual implementation.
 *
 * @author Edmund Miller
 */
@PackageScope
@CompileStatic
class NFNotifyConfig {

    String key  // TODO: Replace this example attribute.

    /**
     * Construct a configuration instance.
     *
     * @param map A nextflow plugin wrapper instance.
     */
    NFNotifyConfig(Map map) {
        final Map config = map ?: [:]
        // TODO: Replace this example assignment.
        this.key = config.key ?: 'default value'
    }

}
