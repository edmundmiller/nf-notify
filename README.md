# nf-notify plugin

## Terminal Notifications for Nextflow

**nf-notify** is a Nextflow plugin that adds native terminal notifications when your workflow completes. It works out-of-the-box with both regular Nextflow runs and [nf-test](https://github.com/nextflow-io/nf-test) for pipeline testing.

### How to Use

#### Recommended: Enable Globally

To receive notifications for all pipelines, add the following to your `~/.nextflow/config`:

```groovy
plugins {
    id 'nf-notify@0.1.0'
}
notify {
    enabled = true
}
```

#### Per-Pipeline Usage

Alternatively, add the same block to your pipeline's `nextflow.config`.

#### What Happens?

When your workflow completes, a native terminal notification will be sent (using OSC 9, iTerm2, or compatible protocols). This works in most modern terminals, including iTerm2 and recent versions of GNOME Terminal.

#### What Terminals are Supported?

- Ghostty[^1]
- iTerm2[^2]
- GNOME Terminal[^3]
- Konsole[^4]
- Terminal.app[^5]
- Kitty[^6]
- WezTerm[^7]
- Foot (OSC 777 only)[^8]

#### nf-test

No special configuration is needed! If you run your pipeline with [nf-test](https://github.com/nextflow-io/nf-test), you will also receive notifications on test completion. These can stack up quickly though.

### Example

See `examples/nextflow.config` and `examples/test-pipeline.nf` for a minimal setup.

### More Information

- Community post: [Native terminal notifications on pipeline completion](https://community.seqera.io/t/native-terminal-notifications-on-pipeline-completion/1289)

[^1]: [Ghostty changelog](https://github.com/mitchellh/ghostty/releases)
[^2]: [iTerm2 escape codes documentation](https://iterm2.com/documentation-escape-codes.html)
[^3]: [GNOME Terminal 3.44 release notes](https://gitlab.gnome.org/GNOME/gnome-terminal/-/merge_requests/233)
[^4]: [Konsole merge request](https://invent.kde.org/utilities/konsole/-/merge_requests/670)
[^5]: [Terminal.app Ventura release notes](https://support.apple.com/en-us/HT213264)
[^6]: [Kitty protocol extensions](https://sw.kovidgoyal.net/kitty/protocol-extensions.html)
[^7]: [WezTerm changelog](https://wezfurlong.org/wezterm/changelog.html#20230712-072601-f4abf8fd)
[^8]: [Foot OSC 777 implementation](https://codeberg.org/dnkl/foot/issues/224)

## Building

To build the plugin:

```bash
make assemble
```

## Testing with Nextflow

The plugin can be tested without a local Nextflow installation:

1. Build and install the plugin to your local Nextflow installation: `make install`
2. Run a pipeline with the plugin: `nextflow run hello -plugins nf-notify@0.1.0`

## Publishing

Plugins can be published to a central plugin registry to make them accessible to the Nextflow community.

Follow these steps to publish the plugin to the Nextflow Plugin Registry:

1. Create a file named `$HOME/.gradle/gradle.properties`, where $HOME is your home directory. Add the following properties:

   - `pluginRegistry.accessToken`: Your Nextflow Plugin Registry access token.

2. Use the following command to package and create a release for your plugin on GitHub: `make release`.

> [!NOTE]
> The Nextflow Plugin registry is currently available as private beta technology. Contact info@nextflow.io to learn how to get access to it.
