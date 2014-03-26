(ns prometheus.unit.t_core
  (:require
    [midje.sweet :refer :all]
    [prometheus.core :as prometheus]))

(facts "about content negotiation"
  (fact "accepts prometheus protobuf requests"
    (let [request {:headers {"accept" "application/vnd.google.protobuf;proto=io.prometheus.client.MetricFamily;encoding=delimited;q=0.7,application/json;schema=prometheus/telemetry;version=0.0.2;q=0.2,*/*;q=0.1"}}]
      (prometheus/accepts-proto-response request) => TRUTHY))
  (fact "rejects any other request"
    (let [request {:headers {"accept" "*/*"}}]
      (prometheus/accepts-proto-response request) => FALSEY)))