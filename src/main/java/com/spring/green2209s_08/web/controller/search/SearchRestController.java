package com.spring.green2209s_08.web.controller.search;

import com.spring.green2209s_08.web.constants.SessionConst;
import com.spring.green2209s_08.web.controller.StatusResponse;
import com.spring.green2209s_08.web.domain.Item;
import com.spring.green2209s_08.web.service.ItemService;
import com.spring.green2209s_08.web.service.QueryService;
import com.spring.green2209s_08.web.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchRestController {
    private final ItemService itemService;
    private final ReviewService reviewService;
    private final QueryService queryService;

    @PostMapping("/query")
    public ResponseEntity<StatusResponse> query(
            @RequestParam String content, @RequestParam Long itemId, HttpServletRequest request){

        HttpSession session = request.getSession(false);

        Long memberId = (Long) session.getAttribute(SessionConst.MEMBER_ID);

        queryService.writeItemQuery(itemId, memberId, content);

        StatusResponse statusResponse = new StatusResponse(
                HttpStatus.CREATED.toString(), "문의글 작성 완료", "TRUE"
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(statusResponse);
    }
}
