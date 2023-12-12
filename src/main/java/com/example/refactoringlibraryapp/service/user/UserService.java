package com.example.refactoringlibraryapp.service.user;

import com.example.refactoringlibraryapp.domain.user.User;
import com.example.refactoringlibraryapp.domain.user.UserRepository;
import com.example.refactoringlibraryapp.dto.search.response.BookSearchResponse;
import com.example.refactoringlibraryapp.dto.search.response.UserSearchResponse;
import com.example.refactoringlibraryapp.dto.user.request.UserReqeust;
import com.example.refactoringlibraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    // # 사용자 등록 : 휴대폰 번호로 등록된 사용자가 존재하는지 -> 없으면 신규등록
    @Transactional
    public void createUser(UserReqeust request) {
        if (repository.existsByPhone(request.getPhone())) {
            throw new IllegalArgumentException("이미 사용 중인 연락처 또는 등록된 사용자입니다.");
        }
        repository.save(new User(request.getName(), request.getAge(), request.getPhone()));
        //System.out.println(request.getName() + "'s join is complete~");
    }

    // # 사용자 리스트 출력
    @Transactional
    public List<UserResponse> userList() {
        List<UserResponse> userList = repository.findAll().stream()
                                        .map(user -> new UserResponse(user))
                                        .collect(Collectors.toList());

        if (userList.isEmpty())
            throw new IllegalArgumentException("등록된 사용자가 존재하지 않습니다.");

        return userList;
    }


    // # 사용자 수정
    // 1. 사용자 id로 사용자 정보 가지고 오기
    @Transactional
    public UserResponse getUser(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        return new UserResponse(user);
    }

    // 2. 입력받은 연락처 번호가 다른 사용자가 사용 중인지 -> (아니라면 수정 진행// 맞다면 예외 던지기)
    @Transactional
    public void updateUser(UserReqeust request) {
        boolean check = repository.existsByPhoneAndIdNot(request.getPhone(), request.getId());
        if (check) {
            throw new IllegalArgumentException("이미 사용 중인 연락처입니다.");
        }

        // 수정 진행 (변경 가능한 데이터 : 이름, 연락처, 나이
        User user = repository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        user.updateUser(request.getName(), request.getPhone(), request.getAge());
    }

    @Transactional
    public void userDelete(Long id) {
        boolean check = repository.existsById(id);
        if (!check){
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }
        repository.deleteById(id);
    }

    @Transactional
    public List<UserSearchResponse> userSearch(String username, String phone){

        List<Object[]> searchResults = repository.userSearch(username, phone);

        List<UserSearchResponse> userSearchResponseList = new ArrayList<UserSearchResponse>();

        for (Object[] row : searchResults) {
            String userResult = (String) row[0]; //userName
            String phoneResult = (String) row[1]; //phone

            String bookName = "";
            if ( row[2] != null )
                bookName = (String) row[2]; // bookName

            String writer = "";
            if (row[3] != null )
                writer = (String) row[3]; // writer

            String loanDate = "";
            if (row[4] != null)
                loanDate = (String) row[4]; // loanDate

            String returnDate = "";
            if (row[4] != null && row[5] != null)
                returnDate = (String) row[5]; // returnDate

            userSearchResponseList.add(new UserSearchResponse(userResult,phoneResult, bookName, writer, loanDate, returnDate));
        }
        
        // if(userSearchReponseList.isEmpty()) 랑 같음
        if (userSearchResponseList.size() == 0){
            throw new IllegalArgumentException("검색 결과가 존재하지 않습니다.");
        }

        return userSearchResponseList;
    }
}
