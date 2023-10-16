# 🖥 TechLabs AI (Eigene Korea) - Backend Software Engineer Coding Test


## 🛠 개발 환경

- **JAVA 11**
- **Spring boot 2.5.6**
- **MySQL 8.0.33**
- **Postman**

## 🚀 빌드 방법

### csv 파일 업로드
- src/main/resources로 이동
- "csv" 디렉토리 생성
- product.csv, rec.csv 업로드

## ✅ 수행 내역

1. 어플리케이션 실행과 동시에 @PostConstruct로 product.csv, rec.csv를 db에 import하는 방법 선택
2. 조회 API의 경우 개발 사항 1번에 대한 조회만 남겨두고 상품과 연관상품에 대한 각각의 INSERT, UPDATE, DELETE 구현
3. 각각의 Request에 Validation 검증 추가
4. [API Documentation](https://documenter.getpostman.com/view/17476163/2s9YJhvK3K)

## 🔍 개발사항 2번 참고 목록 

1. 추가적인 CREATE, UPDATE, DELETE 작업에 한해 target_item과 result_item 모두 Product DB에 담겨져 있다고 가정
2. Rec Update의 경우 동일 상품 및 연관 상품에 대한 score와 rank 값을 수정 하는 방식 
3. Delete의 경우 각각의 레코드 삭제