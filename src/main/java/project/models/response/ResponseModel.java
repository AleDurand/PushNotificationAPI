package project.models.response;

import java.util.List;

public class ResponseModel {

	private Long multicastId;
	private Integer success;
	private Integer failure;
	private Integer canonicalIds;
	private List<ResultModel> results;

	public ResponseModel() {
		super();
	}

	public Long getMulticastId() {
		return multicastId;
	}

	public void setMulticastId(Long multicastId) {
		this.multicastId = multicastId;
	}

	public Integer getSuccess() {
		return success;
	}

	public void setSuccess(Integer success) {
		this.success = success;
	}

	public Integer getFailure() {
		return failure;
	}

	public void setFailure(Integer failure) {
		this.failure = failure;
	}

	public Integer getCanonicalIds() {
		return canonicalIds;
	}

	public void setCanonicalIds(Integer canonicalIds) {
		this.canonicalIds = canonicalIds;
	}

	public List<ResultModel> getResults() {
		return results;
	}

	public void setResults(List<ResultModel> results) {
		this.results = results;
	}

}
