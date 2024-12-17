package nextflow.nf_notify

import nextflow.Session
import nextflow.processor.TaskHandler
import nextflow.trace.TraceObserver
import nextflow.trace.TraceRecord
import java.nio.file.Path

class NFNotifyObserver implements TraceObserver {

    private Session session
    private boolean enabled = false

    @Override
    void onFlowCreate(Session session) {
        this.session = session
        // Check if notifications are enabled in config
        enabled = session.config.navigate('notify.enabled', false)
    }

    def sendTerminalNotification(String protocol, String title, String message) {
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

    @Override
    void onFlowComplete() {
        if (!enabled) return

        // Send notifications using different protocols
        sendTerminalNotification("iterm2", "Pipeline Completion", "Workflow completed successfully")
        sendTerminalNotification("osc9", "Pipeline Completion", "Workflow completed successfully")
        sendTerminalNotification("osc777", "Pipeline Completed", "Workflow completed successfully")
    }

    @Override
    void onFlowError(TaskHandler handler, TraceRecord trace) {
        if (!enabled) return

        def errorMsg = "Workflow failed: ${handler?.task?.exitStatus ?: 'unknown error'}"

        // Send error notifications
        sendTerminalNotification("iterm2", "Pipeline Error", errorMsg)
        sendTerminalNotification("osc9", "Pipeline Error", errorMsg)
        sendTerminalNotification("osc777", "Pipeline Error", errorMsg)
    }
}
