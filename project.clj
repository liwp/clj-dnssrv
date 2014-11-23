(defproject clj-dnssrv "0.1.0-SNAPSHOT"
  :description "DNS SRV record lookup for Clojure"
  :url "https://github.com/liwp/clj-dnssrv"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :scm {:name "git"
        :url "https://github.com/liwp/corax"}
  :deploy-repositories [["releases" :clojars]]

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.spotify/dns "2.2.0"]]

  :profiles {:dev {:plugins [[jonase/eastwood "0.1.4"]
                             [listora/whitespace-linter "0.1.0"]]

                   :eastwood {:exclude-linters [:deprecations :unused-ret-vals]}

                   :aliases {"ci" ["do" ["test"] ["lint"]]
                             "lint" ["do" ["whitespace-linter"] ["eastwood"]]}}})
