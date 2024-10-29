package com.tombay33.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.tombay33.domain.ItemCategory;
import com.tombay33.dto.ItemCategoryDTO;
import com.tombay33.dto.ItemCategorySearchDTO;
import com.tombay33.dto.ItemCategoryPageDTO;
import com.tombay33.dto.ItemCategoryConvertCriteriaDTO;
import com.tombay33.service.GenericService;
import com.tombay33.dto.common.RequestDTO;
import com.tombay33.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ItemCategoryService extends GenericService<ItemCategory, Integer> {

	List<ItemCategory> findAll();

	ResultDTO addItemCategory(ItemCategoryDTO itemCategoryDTO, RequestDTO requestDTO);

	ResultDTO updateItemCategory(ItemCategoryDTO itemCategoryDTO, RequestDTO requestDTO);

    Page<ItemCategory> getAllItemCategorys(Pageable pageable);

    Page<ItemCategory> getAllItemCategorys(Specification<ItemCategory> spec, Pageable pageable);

	ResponseEntity<ItemCategoryPageDTO> getItemCategorys(ItemCategorySearchDTO itemCategorySearchDTO);
	
	List<ItemCategoryDTO> convertItemCategorysToItemCategoryDTOs(List<ItemCategory> itemCategorys, ItemCategoryConvertCriteriaDTO convertCriteria);

	ItemCategoryDTO getItemCategoryDTOById(Integer itemCategoryId);







}





