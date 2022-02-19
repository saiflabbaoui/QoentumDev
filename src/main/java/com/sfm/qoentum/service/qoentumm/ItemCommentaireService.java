package com.sfm.qoentum.service.qoentumm;

import java.util.List;

import com.sfm.qoentum.model.qoentumm.ItemCommentaire;

public interface ItemCommentaireService {
	
	ItemCommentaire save(ItemCommentaire itemCommentaire);
	
    List<ItemCommentaire> findAll();
    
    void delete(long id);
    
    ItemCommentaire findById(Long id);
    
}
