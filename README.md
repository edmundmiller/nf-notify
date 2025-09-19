# nf-notify plugin

This project contains a Nextflow plugin called `nf-notify` that provides terminal notifications and graphical progress bars for Nextflow workflows.

The plugin includes:

- A custom trace observer that sends terminal notifications when workflows start and complete
- Support for multiple notification protocols (OSC 9, OSC 777, iTerm2)
- Graphical progress bars using ConEmu OSC 9;4 sequences (supported by Ghostty, Windows Terminal, and other modern terminals)
- Real-time progress tracking with task completion percentage
- Error state handling in progress bars

See the [Nextflow documentation](https://nextflow.io/docs/latest/plugins.html) for more information about developing plugins.

## Plugin structure

- `settings.gradle`

    Gradle project settings.

- `build.gradle`

    Plugin Gradle build file. Project dependencies should be added here.

- `src/main/resources/META-INF/MANIFEST.MF`

    Manifest file defining the plugin attributes e.g. name, version, etc. The attribute `Plugin-Class` declares the plugin main class. This class should extend the base class `nextflow.plugin.BasePlugin` e.g. `nextflow.notify.NotifyPlugin`.

- `src/main/resources/META-INF/extensions.idx`

    This file declares one or more extension classes provided by the plugin. Each line should contain the fully qualified name of a Java class that implements the `org.pf4j.ExtensionPoint` interface (or a sub-interface).

- `src/main/groovy`

    The plugin implementation sources.

- `src/test/groovy`

    The plugin unit tests.

## Plugin classes

- `NotifyPlugin`: the plugin entry point class that extends `nextflow.plugin.BasePlugin`

- `TerminalNotification`: implements `TraceObserver` to handle workflow events and send terminal notifications when workflows start and complete

## Configuration

You can configure the plugin behavior in your `nextflow.config` file:

```groovy
plugins {
    id 'nf-notify'
}

notify {
    progressBar = true  // Enable/disable graphical progress bars (default: true)
}
```

### Progress Bar Support

The plugin sends ConEmu OSC 9;4 progress bar sequences that are supported by:

- **Ghostty** (macOS/Linux) - Native GUI progress bars in taskbar
- **Windows Terminal** (Windows) - Taskbar progress indication
- **ConEmu** (Windows) - Taskbar and tab progress bars
- **Other terminals** - May display progress in tab/window title

The progress bar shows:
- **Indeterminate progress** during workflow initialization
- **Percentage completion** based on completed vs total tasks
- **Error state** if any tasks fail (red progress bar)
- **Automatic cleanup** when workflow completes

## Unit testing 

To run your unit tests, run the following command in the project root directory (ie. where the file `settings.gradle` is located):

```bash
./gradlew check
```

## Testing and debugging

To build and test the plugin during development, configure a local Nextflow build with the following steps:

1. Clone the Nextflow repository in your computer into a sibling directory:
    ```bash
    git clone --depth 1 https://github.com/nextflow-io/nextflow ../nextflow
    ```
  
2. Configure the plugin build to use the local Nextflow code:
    ```bash
    echo "includeBuild('../nextflow')" >> settings.gradle
    ```
  
   (Make sure to not add it more than once!)

3. Compile the plugin alongside the Nextflow code:
    ```bash
    make assemble
    ```

4. Run Nextflow with the plugin by adding the option `-plugins nf-notify` to load the plugin:
    ```bash
    nextflow run nextflow-io/hello -plugins nf-notify
    ```

## Testing without Nextflow build

The plugin can be tested without using a local Nextflow build using the following steps:

1. Build the plugin: `make assemble`
2. Copy the generated plugin JAR to `$HOME/.nextflow/plugins`
3. Create a pipeline that uses your plugin and run it: `nextflow run ./my-pipeline-script.nf -plugins nf-notify`

## Package, upload, and publish

The project should be hosted in a GitHub repository whose name matches the name of the plugin (e.g. `nf-notify`).

Follow these steps to package, upload and publish the plugin:

1. Use the following command to package the plugin:
    ```bash
    ./gradlew assemble
    ```

2. For publishing to the Nextflow plugin registry, refer to the latest Nextflow documentation on plugin publishing.
