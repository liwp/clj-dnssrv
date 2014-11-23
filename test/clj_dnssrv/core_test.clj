(ns clj-dnssrv.core-test
  (:require [clojure.test :refer :all]
            [clj-dnssrv.core :refer :all]))

(deftest ^:e2e test-lookup
  (testing "lookup"
    (let [fqdn "_xmpp-server._tcp.gmail.com"
          results (-> fqdn lookup set)]
      (is (= results
             #{{:host "xmpp-server.l.google.com.", :port 5269}
               {:host "alt1.xmpp-server.l.google.com.", :port 5269}
               {:host "alt2.xmpp-server.l.google.com.", :port 5269}
               {:host "alt3.xmpp-server.l.google.com.", :port 5269}
               {:host "alt4.xmpp-server.l.google.com.", :port 5269}})))))
