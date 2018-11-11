package org.apache.httpcomponents.httpclient;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "from_cache",
        "numResults",
        "term",
        "items"
})
public class Example {

    @JsonProperty("from_cache")
    public Boolean fromCache;
    @JsonProperty("numResults")
    public Integer numResults;
    @JsonProperty("term")
    public String term;
    @JsonProperty("items")
    public List<List<String>> items = null;

    public Boolean getFromCache() {
        return fromCache;
    }

    public void setFromCache(Boolean fromCache) {
        this.fromCache = fromCache;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public List<List<String>> getItems() {
        return items;
    }

    public void setItems(List<List<String>> items) {
        this.items = items;
    }
}