//package com.spring.green2209s_08.web.service;
//
//import com.spring.green2209s_08.web.domain.Category;
//import com.spring.green2209s_08.web.domain.Member;
//import com.spring.green2209s_08.web.domain.Vendor;
//import com.spring.green2209s_08.web.domain.enums.AccountType;
//import com.spring.green2209s_08.web.domain.enums.MemberGrade;
//import com.spring.green2209s_08.web.repository.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.PostConstruct;
//import javax.persistence.EntityManager;
//import java.time.LocalDateTime;
//
//@Component
//@RequiredArgsConstructor
//public class DummyData {
//    private final Dummy dummy;
//
//    @PostConstruct
//    public void init(){
//        dummy.createDummy();
//    }
//
//    @Component
//    @Transactional
//    @RequiredArgsConstructor
//    static class Dummy {
//
//        private final AdminSubRepository adminSubRepository;
//        private final PasswordEncoder encoder;
//        private final EntityManager em;
//        public void createDummy(){
//            String[] fishChildName = {"금붕어", "구피", "베타", "디스커스", "테트라", "플레코", "바브", "기타 열대어"};
//            String[] fishChildCode = {"0101", "0102", "0103", "0104", "0105", "0106", "0107", "0108"};
//
//            String[] goldFishName = {"코메트", "유금", "토좌금", "오란다", "진주린", "난주", "수포안", "기타"};
//            String[] goldFishCode = {"010101", "010102", "010103", "010104", "010105", "010106", "010107", "010108"};
//            String[] guppyName = {"알비노", "블루", "레드", "블랙", "그린", "옐로우", "퍼플", "드래곤", "브론즈", "기타"};
//            String[] guppyCode = {"010201", "010202", "010203", "010204", "010205", "010206", "010207", "010208", "010209", "010210"};
//            String[] bettaName = {"베일테일", "더블테일", "델타", "하프문", "크라운테일", "플라캇", "기타"};
//            String[] bettaCode = {"010301", "010302", "010303", "010304", "010305", "010306", "010307"};
//
//            for(int i=0; i<goldFishName.length; i++){
//                Category category = Category.builder()
//                        .parentId("01")
//                        .parentName("생물")
//                        .childId("0101")
//                        .childName("금붕어")
//                        .grandchildId(goldFishCode[i])
//                        .grandchildName(goldFishName[i])
//                        .build();
//                adminSubRepository.saveCategory(category);
//            }
//            for(int i=0; i<guppyName.length; i++){
//                Category category = Category.builder()
//                        .parentId("01")
//                        .parentName("생물")
//                        .childId("0102")
//                        .childName("구피")
//                        .grandchildId(guppyCode[i])
//                        .grandchildName(guppyName[i])
//                        .build();
//                adminSubRepository.saveCategory(category);
//            }
//            for(int i=0; i<bettaName.length; i++){
//                Category category = Category.builder()
//                        .parentId("01")
//                        .parentName("생물")
//                        .childId("0103")
//                        .childName("베타")
//                        .grandchildId(bettaCode[i])
//                        .grandchildName(bettaName[i])
//                        .build();
//                adminSubRepository.saveCategory(category);
//            }
//            Category fish1 = Category.builder()
//                    .parentId("01")
//                    .parentName("생물")
//                    .childId("0104")
//                    .childName("디스커스")
//                    .grandchildId("010401")
//                    .grandchildName("디스커스/일반/기타")
//                    .build();
//            adminSubRepository.saveCategory(fish1);
//
//            Category fish2 = Category.builder()
//                    .parentId("01")
//                    .parentName("생물")
//                    .childId("0105")
//                    .childName("테트라")
//                    .grandchildId("010501")
//                    .grandchildName("테트라/일반/기타")
//                    .build();
//            adminSubRepository.saveCategory(fish2);
//            Category fish4 = Category.builder()
//                    .parentId("01")
//                    .parentName("생물")
//                    .childId("0106")
//                    .childName("플레코")
//                    .grandchildId("010601")
//                    .grandchildName("플레코/일반/기타")
//                    .build();
//            adminSubRepository.saveCategory(fish4);
//            Category fish5 = Category.builder()
//                    .parentId("01")
//                    .parentName("생물")
//                    .childId("0107")
//                    .childName("바브")
//                    .grandchildId("010701")
//                    .grandchildName("플레코/일반/기타")
//                    .build();
//            adminSubRepository.saveCategory(fish5);
//            Category fish6 = Category.builder()
//                    .parentId("01")
//                    .parentName("생물")
//                    .childId("0108")
//                    .childName("기타 열대어")
//                    .grandchildId("010801")
//                    .grandchildName("기타 열대어")
//                    .build();
//            adminSubRepository.saveCategory(fish6);
//            /* --- ------- --- */
//            Category pr1 = Category.builder()
//                    .parentId("02")
//                    .parentName("비생물")
//                    .childId("0201")
//                    .childName("수초")
//                    .grandchildId("020101")
//                    .grandchildName("수초")
//                    .build();
//            adminSubRepository.saveCategory(pr1);
//            /* --- ------- --- */
//            Category pr2 = Category.builder()
//                    .parentId("02")
//                    .parentName("비생물")
//                    .childId("0202")
//                    .childName("바닥재")
//                    .grandchildId("020201")
//                    .grandchildName("바닥재")
//                    .build();
//            adminSubRepository.saveCategory(pr2);
//            /* --- ------- --- */
//            Category pr3 = Category.builder()
//                    .parentId("02")
//                    .parentName("비생물")
//                    .childId("0203")
//                    .childName("영양제/약품")
//                    .grandchildId("020301")
//                    .grandchildName("물고기영양제/약품")
//                    .build();
//            adminSubRepository.saveCategory(pr3);
//            Category pr4 = Category.builder()
//                    .parentId("02")
//                    .parentName("비생물")
//                    .childId("0203")
//                    .childName("영양제/약품")
//                    .grandchildId("020302")
//                    .grandchildName("수초영양제/약품")
//                    .build();
//            adminSubRepository.saveCategory(pr4);
//            Category pr5 = Category.builder()
//                    .parentId("02")
//                    .parentName("비생물")
//                    .childId("0203")
//                    .childName("영양제/약품")
//                    .grandchildId("020303")
//                    .grandchildName("수질약품")
//                    .build();
//            adminSubRepository.saveCategory(pr5);
//            Category pr6 = Category.builder()
//                    .parentId("02")
//                    .parentName("비생물")
//                    .childId("0204")
//                    .childName("어항")
//                    .grandchildId("020401")
//                    .grandchildName("어항")
//                    .build();
//            adminSubRepository.saveCategory(pr6);
//            Category pr7 = Category.builder()
//                    .parentId("02")
//                    .parentName("비생물")
//                    .childId("0205")
//                    .childName("축양장")
//                    .grandchildId("020501")
//                    .grandchildName("축양장")
//                    .build();
//            adminSubRepository.saveCategory(pr7);
//            Category pr8 = Category.builder()
//                    .parentId("02")
//                    .parentName("비생물")
//                    .childId("0206")
//                    .childName("여과기")
//                    .grandchildId("020601")
//                    .grandchildName("여과기")
//                    .build();
//            adminSubRepository.saveCategory(pr8);
//            Category pr9 = Category.builder()
//                    .parentId("02")
//                    .parentName("비생물")
//                    .childId("0207")
//                    .childName("조명")
//                    .grandchildId("020701")
//                    .grandchildName("조명")
//                    .build();
//            adminSubRepository.saveCategory(pr9);
//            Category pr10 = Category.builder()
//                    .parentId("02")
//                    .parentName("비생물")
//                    .childId("0208")
//                    .childName("히터")
//                    .grandchildId("020801")
//                    .grandchildName("히터")
//                    .build();
//            adminSubRepository.saveCategory(pr10);
//            Category pr11 = Category.builder()
//                    .parentId("02")
//                    .parentName("비생물")
//                    .childId("0209")
//                    .childName("먹이")
//                    .grandchildId("020901")
//                    .grandchildName("먹이")
//                    .build();
//            adminSubRepository.saveCategory(pr11);
//            Category pr12 = Category.builder()
//                    .parentId("02")
//                    .parentName("비생물")
//                    .childId("0210")
//                    .childName("유목")
//                    .grandchildId("021001")
//                    .grandchildName("유목")
//                    .build();
//            adminSubRepository.saveCategory(pr12);
//            Category pr13 = Category.builder()
//                    .parentId("02")
//                    .parentName("비생물")
//                    .childId("0211")
//                    .childName("청소도구")
//                    .grandchildId("021101")
//                    .grandchildName("청소도구")
//                    .build();
//            adminSubRepository.saveCategory(pr13);
//            Category pr14 = Category.builder()
//                    .parentId("02")
//                    .parentName("비생물")
//                    .childId("0212")
//                    .childName("기타")
//                    .grandchildId("021201")
//                    .grandchildName("기타")
//                    .build();
//            adminSubRepository.saveCategory(pr14);
//
//            String password = encoder.encode("1234");
//            Vendor vendor = Vendor.builder()
//                    .vendorLoginId("admin")
//                    .vendorPassword(encoder.encode("1234"))
//                    .vendorName("백정광")
//                    .vendorEmail("baekice@gmail.com")
//                    .vendorPhoneNo("010-5055-5055")
//                    .accountType(AccountType.VENDOR)
//                    .build();
//            em.persist(vendor);
//
//            Member member = Member.builder()
//                    .name("제이콥")
//                    .password(encoder.encode("1234"))
//                    .email("admin")
//                    .point(0)
//                    .phoneNo("01050555055")
//                    .birthDate("1999")
//                    .accountType(AccountType.ADMIN)
//                    .memberGrade(MemberGrade.BRONZE)
//                    .createdTime(LocalDateTime.now())
//                    .accountLock(false)
//                    .build();
//            em.persist(member);
//
//        }
//    }
//}
