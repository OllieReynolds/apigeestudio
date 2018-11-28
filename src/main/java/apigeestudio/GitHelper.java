package apigeestudio;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GitHelper {

	private static List<Git> gitCloneRepositories(RepositoryManifest repositoryManifest) {
		List<Git> listGit = new ArrayList<Git>();
		for (Repository repository : repositoryManifest.getRepositoryManifest()) {
			Git git = gitCloneRepository(repository, repositoryManifest.getDiskLocation());
			listGit.add(git);
		}
		return listGit;
	}

	private static Git gitCloneRepository(Repository repository, String repositoryDirectoryOnDisk) {
		int directorySeperatorIndex = repository.getUrl().lastIndexOf("/");
		String repositoryName = repository.getUrl().substring(directorySeperatorIndex, repository.getUrl().length());
		File fullRepositoryPath = new File(repositoryDirectoryOnDisk + repositoryName);

		if (fullRepositoryPath.exists()) {
			try {
				return Git.open(fullRepositoryPath);
			} catch (IOException e) {
				return null;
			}
		} else {
			try {
				return Git.cloneRepository().setURI(repository.getUrl()).setBranchesToClone(repository.getBranches())
						.setDirectory(fullRepositoryPath).call();
			} catch (GitAPIException e) {
				return null;
			}
		}
	}

	public static RepositoryManifest getGitRepositoryManifest(String resourceName) {
		InputStream repositoryManifestFile = GitHelper.class.getResourceAsStream(resourceName);
		ObjectMapper mapper = new ObjectMapper();
		RepositoryManifest repositoryManifest;
		try {
			repositoryManifest = mapper.readValue(repositoryManifestFile, RepositoryManifest.class);
		} catch (IOException e) {
			return null;
		}
		return repositoryManifest;
	}

	private static Stream<String> getCommitMessages(Git git) {
		try {
			return StreamSupport.stream(git.log().call().spliterator(), true).map( e -> e.getFullMessage());
		} catch (GitAPIException e) {
			return null;
		}
	}

	private static List<String> gitCommitMessages(List<Git> listGit) {
		List<String> fullOutput = new ArrayList<String>();
		for (Git git : listGit) {
			fullOutput.add("#############################\n" + git.toString());
			Stream<String> commitMessages = getCommitMessages(git);
			fullOutput.addAll(commitMessages.collect(Collectors.toList()));
		}
		return fullOutput;
	}

	public static void main(String[] args) {
		RepositoryManifest repositoryManifest = getGitRepositoryManifest("RepositoryManifest.json");
		List<Git> listGit = gitCloneRepositories(repositoryManifest);
		List<String> listGitCommitMessages = gitCommitMessages(listGit);
	}
}
