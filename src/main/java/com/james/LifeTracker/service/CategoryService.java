package com.james.LifeTracker.service;

import com.james.LifeTracker.db.entity.Category;
import com.james.LifeTracker.db.repository.CategoryRepository;
import com.james.LifeTracker.dto.binding.CategoryInputBindingModel;
import com.james.LifeTracker.dto.view.CategoryViewModel;
import com.james.LifeTracker.error.CategoryNotFoundException;
import com.james.LifeTracker.util.DateFormat;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserDetailsServiceImpl userService;
    private final ModelMapper modelMapper;

    public CategoryService(CategoryRepository categoryRepository, UserDetailsServiceImpl userService, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Category createCategory(CategoryInputBindingModel categoryModel, String userName){
        Category category = this.modelMapper.map(categoryModel, Category.class);
        category.setUser(this.userService.loadUserByUsername(userName));
        return this.categoryRepository.save(category);
    }

    public Category findCategoryById(Long categoryId){
        return this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category with given id was not found!"));
    }

    public List<Category> findAllCategories(){
        return this.categoryRepository.findAll();
    }

    public List<CategoryViewModel> findCategoriesByUser(String username){

        List<CategoryViewModel> readyCategories = this.categoryRepository.findAll()
                .stream()
                .filter(o -> o.getUser().getUsername().equals(username))
                .map(o -> this.modelMapper.map(o, CategoryViewModel.class))
                .collect(Collectors.toList());

        //Manual mappings for the harder stuff
        for (var category: readyCategories) {
            category.setCreatedOn(DateFormat.getDateString(this.findCategoryById(category.getId()).getCreatedOn()));
            category.setLastUpdatedOn(DateFormat.getTimeAgo(this.findCategoryById(category.getId()).getLastUpdatedOn()));
            category.setEventCount(this.findCategoryById(category.getId()).getEvents().size());
            category.setProblemCount(this.findCategoryById(category.getId()).getProblems().size());
            category.setNoteCount(this.findCategoryById(category.getId()).getNotes().size());
        }

        return readyCategories;
    }


}

//TODO: NA VIEWTATA NA VSICHKI KATEGORII DA SA LISTNATI KOLKO NOTE,
// PROBLEM I EVENTI IMA KAKTO I KUDE SA NAI GOLQM PROCENT RAZRESHENI PROBLEMI ,
// I SQKASH DA VKARAM NQKWA IDEQ NA KOE DA OBRUSHTASH POWECHE VNIMANIE DA E V CHERVENO PRIMERNO
// eventite mi da sa primerno 10 izpulneni ot 40 i tn.