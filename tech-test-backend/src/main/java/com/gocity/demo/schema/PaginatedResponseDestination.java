package com.gocity.demo.schema;

import java.util.List;

public class PaginatedResponseDestination {
	private Integer pageSize;
	private Integer pageNumber;
	private Integer total;
	private List<Destinations> results;
	
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
	public List<Destinations> getResults() {
		return results;
	}
	public void setResults(List<Destinations> results) {
		this.results = results;
	}
	
	public PaginatedResponseDestination(Integer pageSize, Integer pageNumber, Integer total, List<Destinations> results) {
		super();
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
		this.total = total;
		this.results = results;
	}
	
	public PaginatedResponseDestination() {
		super();
	}
	public static PaginatedResponseDestination.PaginatedResponseDestinationBuilder builder() {
        return new PaginatedResponseDestination.PaginatedResponseDestinationBuilder();
    }
	
	 public static class PaginatedResponseDestinationBuilder {
	    	private Integer pageSize;
	        private Integer pageNumber;
	        private Integer total;
	        private List<Destinations> results;
	        
	        PaginatedResponseDestinationBuilder() { }
	        
	        public PaginatedResponseDestination.PaginatedResponseDestinationBuilder pageSize(final Integer pageSize) {
	            this.pageSize = pageSize;
	            return this;
	        }

	        public PaginatedResponseDestination.PaginatedResponseDestinationBuilder pageNumber(final Integer pageNumber) {
	            this.pageNumber = pageNumber;
	            return this;
	        }

	        public PaginatedResponseDestination.PaginatedResponseDestinationBuilder total(final  Integer total) {
	            this.total = total;
	            return this;
	        }
	        
	        public PaginatedResponseDestination.PaginatedResponseDestinationBuilder results(final List<Destinations> results) {
	            this.results = results;
	            return this;
	        }
	        
	        public PaginatedResponseDestination build() {
	            return new PaginatedResponseDestination(this.pageSize, this.pageNumber, this.total, this.results);
	        }

	        public String toString() {
	            return "PaginatedResponseDestination.PaginatedResponseDestinationBuilder(pageSize=" + this.pageSize + ", pageNumber=" + this.pageNumber + ", total=" + this.total + ", results=" + this.results +")";
	        }
	    }
}