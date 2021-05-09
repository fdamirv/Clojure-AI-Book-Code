(ns knowledge-graph-navigator-clj.sparql
  (:require [clj-http.client :as client])
  (:require clojure.stacktrace)
  (:require [cemerick.url :refer (url-encode)])
  (:require [clojure.data.csv :as csv]))

;; Copied from https://github.com/mark-watson/clj-sparql

(def USE-LOCAL-GRAPHDB true)

(defn dbpedia [sparql-query]
  ;;(let [q (str "https://dbpedia.org//sparql?output=csv&query=" (url-encode sparql-query))
  (let [q (str "http://127.0.0.1:8080/sparql?output=csv&query=" (url-encode sparql-query))
        _ (println q)
        response (client/get q)
        body (:body response)]
    (csv/read-csv body)))

(defn- graphdb-helper [host port graph-name sparql-query]
  (let [q (str host ":" port "/repositories/" graph-name "?query=" (url-encode sparql-query))
        response (client/get q)
        body (:body response)]
    (csv/read-csv body)))

(defn graphdb
  ([graph-name sparql-query] (graphdb-helper "http://127.0.0.1" 7200 graph-name sparql-query))
  ([host port graph-name sparql-query] (graphdb-helper host port graph-name sparql-query)))

(defn sparql-endpoint [sparql-query]
  ;;(println "\nSPARQL:\n" sparql-query)
  (try
    (if USE-LOCAL-GRAPHDB
      (graphdb "dbpedia" sparql-query)
      (dbpedia sparql-query))
    (catch Exception e
      (do
        (println "WARNING: a SPARQL query failed:\n" sparql-query)
        (println (.getMessage e))
        (clojure.stacktrace/print-stack-trace e)
        []))))

(defn -main
  "I don't do a whole lot."
  [& _]
  (println (sparql-endpoint "select * { ?s ?p ?o } limit 10")))
