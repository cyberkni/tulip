package net.jgit;

import java.io.File;
import java.io.FileWriter;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.revwalk.RevCommit;

/**
 * 
 * @author lf E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年10月16日 下午5:27:55
 */
public class JGit {

	public static void main(String[] args) throws Exception {
		Git git = Git.open(new File("/Users/yp/workspace/ocs"));
		for (RevCommit revCommit : git.log().call()) {
			System.out.println("CommitTime : " + revCommit.getCommitTime());
			System.out.println("FullMessage : " + revCommit.getFullMessage());
			PersonIdent committerIdent = revCommit.getCommitterIdent();
			System.out.println(committerIdent.getName() + " " + committerIdent.getEmailAddress() + "\n\n");
		}
	}

	public static void main0(String[] args) throws Throwable {
		File root = new File("/home/lf/jgit");
		if (!root.exists()) {
			root.mkdir();
		}
		File gitF = new File("/home/lf/jgit/.git");
		if (!gitF.exists()) {// 如果已经初始化过,那肯定有.git文件夹
			// 初始化git库,相当于命令行的 git init
			Git.init().setDirectory(root).call();
		}
		Git git = Git.open(root); // 打开git库
		// 好吧,随便写一个文件进去先
		File newFile = new File("/home/lf/jgit/" + System.currentTimeMillis() + ".java");
		FileWriter fw = new FileWriter(newFile);
		fw.write(System.currentTimeMillis() + " ABC");
		fw.flush();
		fw.close();
		// 添加文件咯,相当于 git add .
		git.add().addFilepattern(".+").call();
		// 然后当然是提交啦,相当于 git commit
		git.commit().setCommitter("liufeiit", "liufei_it@126.com").setMessage("Try jgit!").call();
		// 接下来,我们看看log信息
		for (RevCommit revCommit : git.log().call()) {
			System.out.println(revCommit);
			System.out.println(revCommit.getFullMessage());
			System.out.println(revCommit.getCommitterIdent().getName() + " "
					+ revCommit.getCommitterIdent().getEmailAddress());
		}
	}
}
