package apigeestudio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "RepositoryManifest", "DiskLocation" })
public class RepositoryManifest {

	@JsonProperty("RepositoryManifest")
	private List<Repository> repositoryManifest = null;
	@JsonProperty("DiskLocation")
	private String diskLocation;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("RepositoryManifest")
	public List<Repository> getRepositoryManifest() {
		return repositoryManifest;
	}

	@JsonProperty("RepositoryManifest")
	public void setRepositoryManifest(List<Repository> repositoryManifest) {
		this.repositoryManifest = repositoryManifest;
	}

	public RepositoryManifest withRepositoryManifest(List<Repository> repositoryManifest) {
		this.repositoryManifest = repositoryManifest;
		return this;
	}

	@JsonProperty("DiskLocation")
	public String getDiskLocation() {
		return diskLocation;
	}

	@JsonProperty("DiskLocation")
	public void setDiskLocation(String diskLocation) {
		this.diskLocation = diskLocation;
	}

	public RepositoryManifest withDiskLocation(String diskLocation) {
		this.diskLocation = diskLocation;
		return this;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public RepositoryManifest withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}