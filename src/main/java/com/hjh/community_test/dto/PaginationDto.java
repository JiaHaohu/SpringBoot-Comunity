package com.hjh.community_test.dto;

import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDto {

    private List<QusetionDto> qusetions;
    private Boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    
    private Integer page;
    
    private List<Integer> pages = new ArrayList<>();

    private Integer totalPage;


    public void setPagination(Integer totalCount, Integer page, Integer size) {

        this.page=page;



        Integer totalPage=0;
        if (totalCount%size==0){
            totalPage =totalCount/size;
        }else {
            totalPage= totalCount/size+1;
        }

        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }
        this.page=page;
        this.totalPage=totalPage;
        if (page<4){
            if (page+3<totalPage){
            for (int i = 1; i <= page+3 ; i++) {
                pages.add(i);
                }
            }else {
                for (int i = 1; i <= totalPage; i++) {
                    pages.add(i);
                }
            }
        }else if (page>totalPage-4){
            for (int i = page-3; i <= totalPage ; i++) {
                pages.add(i);
            }
        }else {
            for (int i = page-3; i <= page+3; i++) {
                pages.add(i);
            }
        }

        //SHI
        if (page == 1){
            showPrevious=false;
        }else {
            showPrevious =true;
        }
        if (page == totalPage){
            showNext =false;}
        else {
            showNext =true;
        }

        //是否展示第一页
        if (pages.contains(1)){
            showFirstPage = false;
        }else {
            showFirstPage =true;
        }
        //是否展示最后一页
        if (pages.contains(totalPage)){
            showEndPage = false;
        }else {
            showEndPage =true;
        }
    }
}
