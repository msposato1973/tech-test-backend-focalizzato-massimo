package com.gocity.demo.schema;

import java.util.List;

public class PaginatedResponseAttraction {
	
	private Integer pageSize;
	private Integer pageNumber;
	private Integer total;
	private List<Attractions> results;
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<Attractions> getResults() {
		return results;
	}
	public void setResults(List<Attractions> results) {
		this.results = results;
	}
	
	/***
	 * Def. Constructor
	 */
	public PaginatedResponseAttraction() {
		super();
	}
	
	/***
	 * Def. Constructor
	 * 
	 * @param pageSize
	 * @param pageNumber
	 * @param total
	 * @param results
	 */
	public PaginatedResponseAttraction(Integer pageSize, Integer pageNumber, Integer total, List<Attractions> results) {
		super();
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
		this.total = total;
		this.results = results;
	}
	
	public static PaginatedResponseAttraction.PaginatedResponseAttractionBuilder builder() {
        return new PaginatedResponseAttraction.PaginatedResponseAttractionBuilder();
    }
	
	public static class PaginatedResponseAttractionBuilder {
    	private Integer pageSize;
        private Integer pageNumber;
        private Integer total;
        private List<Attractions> results;
        
        PaginatedResponseAttractionBuilder() { }
        
        public PaginatedResponseAttraction.PaginatedResponseAttractionBuilder pageSize(final Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public PaginatedResponseAttraction.PaginatedResponseAttractionBuilder pageNumber(final Integer pageNumber) {
            this.pageNumber = pageNumber;
            return this;
        }

        public PaginatedResponseAttraction.PaginatedResponseAttractionBuilder total(final  Integer total) {
            this.total = total;
            return this;
        }
        
        public PaginatedResponseAttraction.PaginatedResponseAttractionBuilder results(final List<Attractions> results) {
            this.results = results;
            return this;
        }
        
        public PaginatedResponseAttraction build() {
            return new PaginatedResponseAttraction(this.pageSize, this.pageNumber, this.total, this.results);
        }

        public String toString() {
            return "PaginatedResponseAttraction.PaginatedResponseAttractionBuilder(pageSize=" + this.pageSize + ", pageNumber=" + this.pageNumber + ", total=" + this.total + ", results=" + this.results +")";
        }
    }
}