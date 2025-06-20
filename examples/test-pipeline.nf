#!/usr/bin/env nextflow

// Simple test pipeline to test the nf-notify plugin
process sayHello {
    script:
    """
    echo "Hello from the test pipeline!"
    sleep 2
    echo "Process completed"
    """
}

workflow {
    sayHello()
} 