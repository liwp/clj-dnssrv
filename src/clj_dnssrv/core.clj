(ns clj-dnssrv.core
  (:import [com.spotify.dns DnsSrvResolvers]))

(declare resolver)

(defn resolver
  "Create a new SRV record resolver based on the provided options.

  Options map:
  :cache-lookups
  Enable result caching in a thread local cache (default: false).

  :lookup-timeout-ms
  Set the DNS lookup timeout in milliseconds (default: 5000ms)

  :metered
  Enable Munin metrics reporting. See http://git.io/Ag8Njw and
  http://git.io/5q1j6A for more details (default: false).

  :retain-data
  Return cached results when DNS is unavailable, or DNS returns an
  empty result set (default: false)."
  [& [{:keys [cache-lookups lookup-timeout-ms metered retain-data]
       :or {cache-lookups false
            lookup-timeout-ms 5000
            metered nil
            retain-data false}}]]
  (-> (DnsSrvResolvers/newBuilder)
      (.cachingLookups cache-lookups)
      (.dnsLookupTimeoutMillis lookup-timeout-ms)
      (.metered metered)
      (.retainingDataOnFailures retain-data)
      .build))

(def ^:dynamic *resolver*
  "By default *resolver* is a non-caching, unmetered,
  non-data-retaining DSN SRV record resolver. Use a `binding` form to
  dynamically rebind the var to another resolver instance."
  (resolver {:retain-data true :cache-lookups true}))

(defn- host-and-name->map
  [hn]
  {:host (.host hn)
   :port (.port hn)})

(defn lookup
  "Lookup the SRV records for the given fully qualified domain name
  with a resolver.

  The single-arity fn will use *resolver* for looking up the fqdn. The
  two-arity fn will use the provided resolver."
  ([fqdn]
     (lookup *resolver* fqdn))
  ([resolver fqdn]
     (map host-and-name->map (.resolve resolver fqdn))))
