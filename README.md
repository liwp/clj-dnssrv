# clj-dnssrv

[![Build Status](https://travis-ci.org/liwp/clj-dnssrv.svg)](https://travis-ci.org/liwp/clj-dnssrv)

A Clojure library for looking up DNS SRV records.

## Usage

The simplest way to lookup SRV resords for a fully qualified domain
name is to just call the `lookup` function:

```clj
(require '[clj-dnssrv.core :as srv])

(srv/lookup "_xmpp-server._tcp.gmail.com")
;; => ({:host "alt4.xmpp-server.l.google.com.", :port 5269}
 {:host "alt1.xmpp-server.l.google.com.", :port 5269}
 {:host "alt2.xmpp-server.l.google.com.", :port 5269}
 {:host "xmpp-server.l.google.com.", :port 5269}
 {:host "alt3.xmpp-server.l.google.com.", :port 5269})
```

By default the `lookup` function will use the default DNS resolver
with `:retain-data`, `:cache-lookup` and a `:lookup-timeout` of 5000
milliseconds.

Alternatively you can create a new resolver with `resolver` and pass that to `lookup`:

```clj
(require '[clj-dnssrv.core :as srv])

(def my-resolver (srv/resolver {:cache-lookups true
                                :lookup-timeout-ms 10000
                                :metered true
                                :retain-data true}))

(srv/lookup my-resolver "_xmpp-server._tcp.gmail.com")
;; => ({:host "alt4.xmpp-server.l.google.com.", :port 5269}
 {:host "alt1.xmpp-server.l.google.com.", :port 5269}
 {:host "alt2.xmpp-server.l.google.com.", :port 5269}
 {:host "xmpp-server.l.google.com.", :port 5269}
 {:host "alt3.xmpp-server.l.google.com.", :port 5269})
```

See the `resolver` docstring for more details on the configuration options.

## License

Copyright © 2014 Lauri Pesonen

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
