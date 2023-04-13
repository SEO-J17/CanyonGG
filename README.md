# 📱CanyonGG

<img src=https://user-images.githubusercontent.com/59912150/231259073-07daa970-eacb-48b8-917c-6a58b689ff17.png width="120" height="120">

+ Riot Games에서 개발한 "리그 오브 레전드"라는 게임의 유저들을 검색해서 게임 전적,매치 정보, 티어 정보등와 같은 게임 정보를 사용자에게 제공하는 프로젝트 입니다.

# 🎤 Introduce

+ 리그 오브 레전드 게임을 즐기는 유저들의 닉네임을 안다면 검색하여 해당 유저의 게임 전적과 같이 플레이한 팀원들의 정보, KDA, 랭크등 다양한 정보를 알아 볼 수 있습니다
+ 유저 정보 등록하기 기능이 있어서 정보를 한 번 등록하면 검색하지 않아도 앱을 실행하자마자 바로 정보를 볼 수 있습니다.
+ 즐겨찾기 기능을 통해 다른 유저를 즐겨찾기에 등록하여 검색을 하지 않아고 한 번의 클릭으로 유저 정보를 알 수 있습니다.
+ 게임 전적을 받아올 때 페이징을 사용했지만 라이엇에서 API 콜 제한을 두었기 때문에 게임 전적 20개만 받아오도록 설정했습니다.

# 🛠 Tech & Library

+ **Min SDK lvevel 26**
+ Kotlin 1.7.0
+ JetPack
    + LifeCycle
    + LiveData
    + flow
    + ViewModel
    + Room
    + DataBinding
    + ViewBinding
    + Navigation
    + DataStore
    + WorkManager
    + Paging3
+ Hilt
+ Glide
+ Retrofit
+ OkHttp
+ Moshi
+ Coroutine
+ SafeArgs
+ Firebase Auth
+ Firebase Remote Config
+ ViewPager2

# ✨Features

> **화면 구성**
<table>
<tr>
<td>홈 화면</td>
<td>검색 화면</td>
<td>설정 화면</td>
</tr>
<tr>
<td><img src=https://user-images.githubusercontent.com/59912150/231479947-87d0caf2-e98c-40a5-adf9-a28da9d04294.gif width="300"></td>
<td><img src=https://user-images.githubusercontent.com/59912150/231480265-e9b91e35-b9ad-4044-b108-6cf511c9b6f2.png width="300"></td>
<td><img src=https://user-images.githubusercontent.com/59912150/231480631-2d174789-ab71-4b52-9f2b-93cdf3dfc8be.png width="300"></td>
</tr>
</table>

> **로그인, 회원가입 기능**
<table>
<tr>
<td>로그인</td>
<td>회원가입</td>
</tr>
<td><img src=https://user-images.githubusercontent.com/59912150/231688077-5203bf73-a069-46ee-9ae2-186cf97e7241.gif width="300"></td>
<td><img src=https://user-images.githubusercontent.com/59912150/231688087-1bfd69f5-6914-4a10-910f-9eedcae3a10c.gif width="300"></td>
</table>

> **검색 기능**
<table>
<tr>
<td>검색 결과</td>
<td>최근 검색한 유저</td>
</tr>
<tr>
<td><img src=https://user-images.githubusercontent.com/59912150/231689527-63b4ca5f-809f-4d92-aa05-20e6c4a4845f.gif width="300"></td>
<td><img src=https://user-images.githubusercontent.com/59912150/231689922-d25dc393-7a46-4056-81c7-05b2ad73af5b.png width="300"></td>
</tr>
</table>

> **즐겨찾기 기능**
<table>
<tr>
<td>즐겨찾기 등록</td>
<td>즐겨찾기 해제</td>
</tr>
<tr>
<td><img src=https://user-images.githubusercontent.com/59912150/231691987-f35cb567-8c30-448b-893a-381f3a8bf1d9.gif width="300"></td>
<td><img src=https://user-images.githubusercontent.com/59912150/231692488-465c6d94-d112-4eca-b59c-60ac7eac8c22.gif width="300"></td>
</tr>
</table>

> **메인 정보 등록 기능**
<table>
<tr>
<td>메인 정보 등록</td>
<td>메인 정보 삭제</td>
</tr>
<tr>
<td><img src=https://user-images.githubusercontent.com/59912150/231694928-80b6e0c1-2d75-4067-ba0b-40346f637b6d.gif width="300"></td>
<td><img src=https://user-images.githubusercontent.com/59912150/231694936-9494e3e5-505e-4ac0-9b5a-a01d580f7b43.gif width="300"></td>
</tr>
</table>

> **전적 상세 화면**
<table>
<tr>
<td>전적 상세 화면</td>
<td>상세 화면 유저 검색</td>
</tr>
<tr>
<td><img src=https://user-images.githubusercontent.com/59912150/231696355-14c847c9-c9ab-4345-9401-01f5dbaac2a2.gif width="300"></td>
<td><img src=https://user-images.githubusercontent.com/59912150/231697824-24c7fa4e-c4e3-4e94-9aca-bdfb97db6b04.gif width="300"></td>
</tr>
</table>

> **테마 설정**
<table>
<tr>
<td>테마 설정 기능</td>
</tr>
<tr>
<td><img src=https://user-images.githubusercontent.com/59912150/231475444-cd8b0427-3e4e-4d21-8fd9-8b3d65767364.gif width="300"></td>
</tr>
</table>

# 🎨Structure

+ MVVM 디자인 패턴 + Google Recomanded Architecture를 이용해 앱을 구성했습니다.

> **MVVM**

  <img src=https://user-images.githubusercontent.com/59912150/229727957-6ba9efc6-032c-433e-b4df-e1c9dbcc7595.png>

> **Google Recomanded Architecture**

  <img src=https://user-images.githubusercontent.com/59912150/231258327-b9545768-9f07-4777-ae88-29f9192108e6.png>

+ [google에 권장 가이드](https://developer.android.com/jetpack/guide?hl=ko)에 따라 Presenter -> Doamin ->
  Data 모듈 순으로 아키텍처를 구성했습니다. 각 화살표는 의존성을 나타냅니다.
