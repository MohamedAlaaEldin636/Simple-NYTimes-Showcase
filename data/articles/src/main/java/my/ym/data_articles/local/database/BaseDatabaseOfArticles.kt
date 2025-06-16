package my.ym.data_articles.local.database

import my.ym.data_articles.local.database.daos.DaoKeyword
import my.ym.data_articles.local.database.daos.DaoRelationSnapshotOfViewedArticlesAndViewedArticle
import my.ym.data_articles.local.database.daos.DaoRelationViewedArticleAndKeyword
import my.ym.data_articles.local.database.daos.DaoSnapshotOfViewedArticles
import my.ym.data_articles.local.database.daos.DaoViewedArticle

/**
 * - Defines DAOs of this module
 */
interface BaseDatabaseOfArticles {

	fun daoKeyword(): DaoKeyword

	fun daoViewedArticle(): DaoViewedArticle

	fun daoSnapshotOfViewedArticles(): DaoSnapshotOfViewedArticles

	fun daoRelationViewedArticleAndKeyword(): DaoRelationViewedArticleAndKeyword

	fun daoRelationSnapshotOfViewedArticlesAndViewedArticle(): DaoRelationSnapshotOfViewedArticlesAndViewedArticle

}
