/*
 * Copyright 2025, Seqera Labs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nextflow.plugin

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import nextflow.Session
import nextflow.trace.TraceObserver

/**
 * Implements an observer that allows implementing custom
 * logic on nextflow execution events.
 */
@Slf4j
@CompileStatic
class NfNotifyObserver implements TraceObserver {

    @Override
    void onFlowComplete() {
        def currentTime = new Date()
        def formattedTime = currentTime.format("yyyy-MM-dd HH:mm:ss")

        sendTerminalNotification(
            "osc9", 
            "Nextflow Workflow Complete",
            formattedTime,
        )
    }

    private void sendTerminalNotification(String protocol, String title, String message) {
        def ESC = "\u001b"
        def BEL = "\u0007"
        def escapeSequence

        switch (protocol.toLowerCase()) {
            case "iterm2":
                escapeSequence = "${ESC}]1337;Notify=title=${title};body=${message}${BEL}"
                break
            case "osc9":
                escapeSequence = "${ESC}]9;${title} ${message}${BEL}"
                break
            case "osc777":
                escapeSequence = "${ESC}]777;notify;${title};${message}${BEL}"
                break
            default:
                println "Unsupported protocol. Using basic print."
                println "${title}: ${message}"
                return
        }

        System.out.print(escapeSequence)
        System.out.flush()
    }
}
