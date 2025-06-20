package nextflow.plugin

import nextflow.Session
import nextflow.trace.TraceObserverV2
import spock.lang.Specification
import java.io.ByteArrayOutputStream
import java.io.PrintStream

/**
 * Test for NfNotifyObserver functionality
 */
class NfNotifyObserverTest extends Specification {

    def 'should create the observer instance' () {
        given:
        def factory = new NfNotifyFactory()
        when:
        def result = factory.create(Mock(Session))
        then:
        result.size() == 1
        result.first() instanceof NfNotifyObserver
        result.first() instanceof TraceObserverV2
    }

    def 'should send notification on workflow completion' () {
        given:
        def observer = new NfNotifyObserver()
        def originalOut = System.out
        def baos = new ByteArrayOutputStream()
        def capturedOut = new PrintStream(baos)
        
        when:
        System.setOut(capturedOut)
        observer.onFlowComplete()
        System.setOut(originalOut)
        
        then:
        def output = baos.toString()
        output.contains('\u001b]9;Nextflow Workflow Complete')
    }
}
