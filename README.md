# Ch2 MSA 과제
## ERD
![MSA 과제](https://github.com/user-attachments/assets/c80bb709-c379-4727-bf87-8dc19795674d)
## 기술 스택
- java17
- springBoot
- eureka
## 프로젝트 구조
- `com.sparta.msa_exam` : eureka server
  - 모든 서비스 애플리케이션의 위치를 저장한느 중앙 저장소 역할 수행
- `com.sparta.msa_exam.auth` : auth service application
  - 회원가입과 로그인을 수행
- `com.sparta.msa_exam.product` : product service application
  - 상품 추가 및 목록 조회 수행
- `com.sparta.msa_exam.order` : order service application
  - 주문 추가, 단건 조회, 수정 수행
- `com.sparta.msa_exam.gateway` : gateway
  - 클라이언트의 요청을 적절한 서비스 애플리케이션으로 라우팅