(defproject anomaly_detection_clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [com.markwatson/anomaly_detection "1.0-SNAPSHOT"]
                 [org.apache.commons/commons-io "1.3.2"]
                 [org.clojure/data.csv "1.0.0"]
                 [incanter "1.9.3"]]
  :main ^:skip-aot anomaly-detection-clj.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
