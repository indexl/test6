package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dto.Article;
import com.example.demo.dto.Board;

@Service
public class ArticleService {
	
	private ArticleDao articleDao;
	
	public ArticleService(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
	public void writeArticle(int loginedMemberId, int boardId, String title, String body) {
		articleDao.writeArticle(loginedMemberId, boardId, title, body);
	}

	public List<Article> getArticles(int boardId, int limitFrom) {
		return articleDao.getArticles(boardId, limitFrom);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}
	
	public int getLastInsertId() {
		return articleDao.getLastInsertId();
	}

	public Board getBoardById(int boardId) {
		return articleDao.getBoardById(boardId);
	}

	public int getArticlesCnt(int boardId) {
		return articleDao.getArticlesCnt(boardId);
	}
}