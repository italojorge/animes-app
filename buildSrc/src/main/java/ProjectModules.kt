object ProjectModules {
    object Root{
        const val DATA_LOCAL = ":data-local"
        const val DI = ":di"
        const val NAVIGATION = ":navigation"
    }

    object Feature {
        private const val FeaturePrefix = ":features:"

        object AUTH {
            private const val FeatureAuthPrefix = "${FeaturePrefix}feature-auth:"
            const val UI = "${FeatureAuthPrefix}ui"
            const val DOMAIN = "${FeatureAuthPrefix}domain"
            const val DATA = "${FeatureAuthPrefix}data"
            const val DATA_REMOTE = "${FeatureAuthPrefix}data-remote"
        }

        object HOME {
            private const val FeatureHomePrefix = "${FeaturePrefix}feature-home:"
            const val UI = "${FeatureHomePrefix}ui"
            const val DOMAIN = "${FeatureHomePrefix}domain"
            const val DATA = "${FeatureHomePrefix}data"
            const val DATA_REMOTE = "${FeatureHomePrefix}data-remote"
        }
    }

    object Base {
        private const val BasePrefix = ":bases:"
        const val CORE = "${BasePrefix}core"
        const val DOMAIN = "${BasePrefix}base-domain"
        const val DATA_REMOTE = "${BasePrefix}base-data-remote"
    }
}
