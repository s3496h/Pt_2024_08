package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.service.ArticleService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleService;

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(int id) {
		Article article = articleService.getArticleById(id);
		if (article == null) {
			return ResultData.from("F-1",Ut.f("%d번 게시글은 없습니다.",id));
		}
		return ResultData.from("S-1",Ut.f("%d번 게시글 입니다.",id),article);
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public Object doModify(int id, String title, String body) {
		
		System.out.println("id : " + id);
		System.out.println("title : " + title);
		System.out.println("body : " + body);
		
		
		Article article = articleService.getArticleById(id);
		if (article == null) {
			return id + "번 글은 없음";
		}
		articleService.modifyArticle(id, title, body);
		return article;
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		Article article = articleService.getArticleById(id);
		if (article == null) {
			return id + "번 글은 없음";
		}
		articleService.deleteArticle(id);
		return id + "번 글이 삭제됨";
	}

	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData<Article> doWrite(String title, String body) {

		if (Ut.isEmptyOrNull(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요");
		}
		if (Ut.isEmptyOrNull(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요");
		}

		ResultData writeArticleRd = articleService.writeArticle(title, body);

		int id = (int) writeArticleRd.getData1();

		Article article = articleService.getArticleById(id);
		return ResultData.from(writeArticleRd.getResultCode(), writeArticleRd.getMsg(), article);
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ResultData<List<Article>> getArticles() {
		
		List<Article> articles = articleService.getArticles();
		
		return ResultData.from("S-1","게시글 목록입니다.",articles);
	}
}