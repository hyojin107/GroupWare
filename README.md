# GroupWare
JavaFX와 **RMI 기술**을 사용하여 만든 그룹웨어 시스템입니다.
Oracle DB와 iBatis를 이용하여 데이터를 저장하였습니다.


## 🙌 목차
1. 개발환경
2. 구현 기능 및 담당역할
3. GroupWare 기능


### 1. 개발 환경 🌞
- JAVA JDK 1.8
- ORACLE DB 11g
- javaFX 8
- JavaMail API 1.6.1


### 2. 구현 기능 및 담당역할 😎
- EXERD를 활용하여 **DB를 설계**하고, 논리,물리ERD를 구현하여 구축
- 단어사전, 용어사전을 정의하여 DB에 저장될 데이터의 이름들을 미리 동일시하기
- 데이터들 사이의 관계를 정의하여 참조 관계 정의
- ORACLE에서 참조관계를 고려하여 DB를 **구축**하고, 더미 데이터를 넣어 프로젝트를 수행 할 수 있는 기본 환경을 구축
- 받은 메일함, 메일 쓰기, 보낸 메일함 구현
- 휴가신청, 휴가 승인/반려, 전직원 휴가현황 구현
- 신입 사원 등록
- 임직원 관리 (사원 리스트 조회, 수정)
- 마이페이지 구현 (프로필 등록 및 수정)
<br/>

### 3. GroupWare 기능 🌞
🔹 **메인페이지** <br/>
<img width="500" alt="로그인" src="https://user-images.githubusercontent.com/49690185/106572144-6d0ec780-657b-11eb-8f42-f289ca129e85.png">
- 접속한 사용자의 프로필 사진이 노출된다.
- 퇴근 버튼을 클릭하여, 간편하게 근태등록을 할 수 있다.
<br/>

🔹 **로그인** <br/>
<img width="600" alt="로그인" src="https://user-images.githubusercontent.com/49690185/106570929-e6a5b600-6579-11eb-8cf0-4bf7cd2599bb.png">
- 로그인 기능
- 비밀번호 찾기 시, 이메일로 임시 비밀번호 발송
<br/>

🔹 **메일** <br/>
<img width="800" alt="받은메일함" src="https://user-images.githubusercontent.com/49690185/106571376-6b90cf80-657a-11eb-908d-9ee9edfd63b4.png">
- javaMail API를 사용하여 받은 메일함 출력
- 받은 메일함 리스트에서 목록을 더블 클릭 시, 상세페이지로 이동

<br/>

<img width="800" alt="메일보내기" src="https://user-images.githubusercontent.com/49690185/106571566-a98df380-657a-11eb-9655-585178a11148.png">
- 메일 쓰기 시, 받는 사람이 여러명일 경우 콤마(,)로 구분하여 여러명에게 전송 가능
- 보낸 메일함에서 보낸 메일을 확인할 수 있다.

<br/><br/>

🔹 **휴가** <br/>
<img width="800" alt="휴가 조회" src="https://user-images.githubusercontent.com/49690185/106572583-f2927780-657b-11eb-9316-2fde0086a14d.png">
- '나의 휴가'에서 본인이 신청한 휴가 내역을 조회
- 관리자 권한(인사팀)으로 로그인 했을 경우에만 '휴가승인/반려'버튼과 '전직원 휴가 현황'버튼이 보여집니다.
- 관리자의 경우에만 전 사원의 휴가 현황을 조회 가능

<br/>

<img width="800" alt="휴가 신청" src="https://user-images.githubusercontent.com/49690185/106572885-55840e80-657c-11eb-8ca2-79038acc4115.png">
- 휴가신청 시, 결재라인을 3명까지 정해야 하며, 콤보박스를 통해 휴가 종류를 선택한 뒤 휴가 신청을 한다.
- 관리자 권한(인사팀)으로 로그인 할 경우에만 승인/반려가 가능하다.

<br/><br/>

🔹 **사원 관리** <br/>
<img width="800" alt="사원관리" src="https://user-images.githubusercontent.com/49690185/106573666-46519080-657d-11eb-8ed1-d6a2c597d5c4.png">
- 사원 관리는 '관리자 권한(인사팀)'만 이용 가능하다.
- 사원 리스트 중 하나를 클릭 시, 사원 상세 정보를 조회할 수 있으며, 수정하기를 통해 수정이 가능하다.
<br/>

🔹 **마이페이지** <br/>
<img width="800" alt="사원관리" src="https://user-images.githubusercontent.com/49690185/106576756-d93ffa00-6580-11eb-817b-b05c7a06ec57.png">
- 본인의 정보를 확인할 수 있으며, 수정하기를 통해 정보 수정이 가능하다.
- 부서명, 부서내선번호, 입사일자를 제외한 모든 정보를 수정할 수 있다.
- '사진 수정'을 통해 사진을 수정할 수 있으며, 사진 삭제를 클릭 시, 기본 사진으로 등록된다.
- 프로필 사진 뿐만 아니라 싸인 이미지도 변경이 가능하다.

