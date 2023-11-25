# <div align=center> :soccer:  Soccer_deploy [![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2Fjanghaneol%2Fsoccer_deploy.git&count_bg=%2379C83D&title_bg=%23111111&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false)](https://hits.seeyoufarm.com)
</div>

## <div align=center> <h3>:grey_exclamation: Stack :grey_exclamation:</h3>  <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white"/> <img src="https://img.shields.io/badge/Java-004088?style=for-the-badge&logo=&logoColor=white"/> <img src="https://img.shields.io/badge/JPA-000000?style=for-the-badge&logo=&logoColor=white"/> <img src="https://img.shields.io/badge/MyBatis-289E6D?style=for-the-badge&logo=&logoColor=white"/> <img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=Oracle&logoColor=white"/><br><img src="https://img.shields.io/badge/Java Script-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white"/> <img src="https://img.shields.io/badge/Bootstrap-7952B3?style=for-the-badge&logo=Bootstrap&logoColor=white"/> <img src="https://img.shields.io/badge/HTML-E34F26?style=for-the-badge&logo=HTML5&logoColor=white"/> <img src="https://img.shields.io/badge/CSS-1572B6?style=for-the-badge&logo=CSS3&logoColor=white"/> <img src="https://img.shields.io/badge/Jquery-0769AD?style=for-the-badge&logo=jQuery&logoColor=white"/><br><img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white"/> </div>

# ![title2](https://user-images.githubusercontent.com/43395355/220569717-68530154-5131-4418-a862-cee00b95b826.png)

### 💡프로젝트 소개:bulb:
Soccer_deploy 프로젝트는 기존 축구동호회에서 일정관리, 경기기록, 엔트리의 공유등을 유연하게 해주기 위한 프로그램입니다.  
> 기존의 축구 동호회들은 보통 SNS나 카페, 혹은 밴드등을 통해 관리되었는데, 이 과정에서 유실되는 기록들이 생길 수 있기도 하고 sns나 카페활동이 젖은 일반 회원들은 일정을 공유받았을 때를 놓치면 경기에 참가할 기회를 놓칠 수도 있습니다.  
우린 이런 불편함을 해소해주고자 순수하게 축구를 위한 동호회가 관리될 수 있도록 기능들을 추가하였습니다. 
---
## 화면 구성 :camera:
|메인페이지|최근경기기록|
|-----|-----|
![image](https://user-images.githubusercontent.com/43395355/222040496-c2deae61-fff6-4db2-b17f-65e3d565c125.png) | ![image](https://user-images.githubusercontent.com/43395355/222040636-e21aa9d1-f293-489f-907c-cdafb109fed4.png) 
|회원가입 페이지|회원상세 페이지|
![image](https://user-images.githubusercontent.com/43395355/222041075-97422b97-3bbb-4d90-908b-c13fcf0ec12c.png) | ![image](https://user-images.githubusercontent.com/43395355/222041160-a0081e67-3392-44b4-8115-86e2efac255e.png)
|경기일정 페이지|경기결과 페이지|
![image](https://user-images.githubusercontent.com/43395355/222041427-ca49fdd7-c698-4ea3-9c2b-3a6647985d71.png) | ![image](https://user-images.githubusercontent.com/43395355/222041578-d277db35-dcb8-440f-bcd0-dccbbce9b681.png)
|참가명단 페이지|경기결과 상세페이지|
![image](https://user-images.githubusercontent.com/43395355/222041841-245b3d56-a4ca-430f-859e-b00ef2bfa6fa.png) | ![image](https://user-images.githubusercontent.com/43395355/222041885-b3fa5784-679b-4819-841e-dd5be696ae63.png)
|순위 페이지|선수명단 페이지|
![image](https://user-images.githubusercontent.com/43395355/222041977-606b7def-3c9c-4917-91b5-7b34e5337cb2.png) | ![image](https://user-images.githubusercontent.com/43395355/222042172-2ca5f751-63fa-422e-8978-59b2b10aa6eb.png)
|포메이션 페이지|내 신청목록 페이지|
![image](https://user-images.githubusercontent.com/43395355/222042255-b4d16189-b836-490a-9ccf-47780ba8bb96.png) | ![image](https://github.com/alzkdpf999/javaproject/assets/100851583/28de9659-b895-4c79-b008-679d929ebb41)
|게시글 목록 페이지|게시글 상세 페이지|
![image](https://user-images.githubusercontent.com/43395355/222043131-16ebc6b8-df33-4b26-b3f9-3e6ca02cac72.png) | ![image](https://user-images.githubusercontent.com/43395355/222043190-6fdfa36d-a4ff-43f0-a276-293af784c84d.png)

# 주요 기능 :mag:
### 유저
  - 회원가입, 정보 수정, 회원탈퇴 기능
  - 로그인하면 등록된 경기에 신청이 가능하고, 신청을 취소할 수도 있습니다.
  - 자유롭게 게시글을 작성하고, 댓글을 달 수 있습니다.
### 경기
  - 경기를 등록하고, 해당 경기에 유저들의 참가신청을 받을 수 있습니다.
  - kakao Map을 이용해 경기장 위치를 유저들에게 제공할 수 있습니다.
  - 가장 최근 경기와, 가장 최근 경기결과를 index에 배치함으로써 유저들에게 참가신청을 유도할 수 있고, 지난경기의 결과를 쉽게 알 수 있습니다.
### 랭크
  - 경기 결과를 토대로 회원들은 월마다 자신의 랭크를 확인할 수 있습니다.
  - 랭크 시스템을 통해 유저들에게 스탯을 쌓는 재미를 줄 수 있고, 참여를 이끌어 낼 수 있습니다.
### 상황판
  - 감독은 경기에 참가신청한 인원을 확인할 수 있고, 이를 토대로 상환판을 작성할 수 있습니다. 
  - 특정쿼터에 특정포지션이 주포지션인 회원들을 효율적으로 배치할 수 있습니다.
  - 이 포메이션을 이미지형태로 저장하여 회원들에게 제공합니다.
### 게시판
  - 로그인되어있는 회원은 자유롭게 게시판의 글과 댓글을 적을 수 있습니다.
  - 공지사항을 통해 관리자는 유저들에게 특이사항을 공유할 수 있습니다.
---
# DB 구조
![image](https://github.com/songhunhwa/songhunhwa.github.com/assets/100851583/92b5c664-256e-4245-b785-921614b8533f)
