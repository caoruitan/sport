package org.cd.sport.service;

import java.util.List;

import org.cd.sport.domain.NewsAttachment;
import org.cd.sport.view.FileView;

/**
 * 新闻业务接口
 * 
 * @author liuyk
 *
 */
public interface NewsAttachmentService {

	public boolean create(String newsId, List<FileView> files);

	public boolean deleteByNewsId(String newsId);

	public List<NewsAttachment> getByNewsId(String newsId);

}
