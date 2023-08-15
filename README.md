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

# 🎨Structure

+ MVVM 디자인 패턴 + Google Recomanded Architecture를 이용해 앱을 구성했습니다.

> **MVVM**

  <img src=https://user-images.githubusercontent.com/59912150/229727957-6ba9efc6-032c-433e-b4df-e1c9dbcc7595.png>

> **Google Recomanded Architecture**

  <img src=https://user-images.githubusercontent.com/59912150/231258327-b9545768-9f07-4777-ae88-29f9192108e6.png>

+ [google에 권장 가이드](https://developer.android.com/jetpack/guide?hl=ko)에 따라 Presenter -> Doamin ->
  Data 모듈 순으로 아키텍처를 구성했습니다. 각 화살표는 의존성을 나타냅니다.

# ✨Features

> **화면 구성**
<table>
<tr>
<td>홈 화면</td>
<td>검색 화면</td>
<td>챔피언 화면</td>
<td>설정 화면</td>
</tr>
<tr>
<td><img src=https://github.com/SEO-J17/CanyonGG/assets/59912150/ac7f4dc7-0876-4508-8dad-7fb4ede4b238 width="300"></td>
<td><img src=https://github.com/SEO-J17/CanyonGG/assets/59912150/765fa8b7-4ba7-4b12-a8b9-cfca2f6711f1 width="300"></td>
<td><img src=https://github.com/SEO-J17/CanyonGG/assets/59912150/52c8875b-b288-4fe5-badc-6d8258e72526 width="300"></td>
<td><img src=https://github.com/SEO-J17/CanyonGG/assets/59912150/7bbf2558-022e-44fb-ada8-c17fc0c1edb8 width="300"></td>
</tr>
</table>

> **로그인, 회원가입 기능**
<table>
<tr>
<td>로그인</td>
<td>회원가입</td>
</tr>
<td><img src=https://github.com/SEO-J17/CanyonGG/assets/59912150/1c3fbb3e-ea35-40cc-a950-a8e296aa7f04 width="300"></td>
<td><img src=https://github.com/SEO-J17/CanyonGG/assets/59912150/98d58654-2e4f-4710-8170-312f7d87dcf3 width="300"></td>
</table>

> **검색 기능**
<table>
<tr>
<td>검색 결과</td>
<td>최근 검색한 유저</td>
</tr>
<tr>
<td><img src=https://github.com/SEO-J17/CanyonGG/assets/59912150/44d97206-811a-4dda-be48-05d83533e941 width="300"></td>
<td><img src=https://github.com/SEO-J17/CanyonGG/assets/59912150/d6dc67c7-e849-4935-b0da-c64bfbf1eba3 width="300"></td>
</tr>
</table>

> **즐겨찾기 기능**
<table>
<tr>
<td>즐겨찾기 등록</td>
<td>즐겨찾기 해제</td>
</tr>
<tr>
<td><img src=https://github.com/SEO-J17/CanyonGG/assets/59912150/a64b4327-b431-4b8c-b15b-51b03742cd84 width="300"></td>
<td><img src=https://github.com/SEO-J17/CanyonGG/assets/59912150/9733e46f-1b6c-44a5-978a-ece2177dd10a width="300"></td>
</tr>
</table>

> **챔피언 즐겨찾기 기능**
<table>
<tr>
<td>챔피언 화면</td>
<td>챔피언 즐겨찾기 </td>
</tr>
<tr>
<td><img src=https://github.com/SEO-J17/CanyonGG/assets/59912150/ec9fa041-093c-42b0-b786-3bf91c361c01 width="300"></td>
<td><img src=https://github.com/SEO-J17/CanyonGG/assets/59912150/308a5991-7a13-4441-922f-68b64fa1d062 width="300"></td>
</tr>
</table>

> **메인 정보**
<table>
<tr>
<td>메인 정보 등록</td>
<td>메인 정보 삭제</td>
<td>메인 정보 새로고침</td>
</tr>
<tr>
<td><img src=https://github.com/SEO-J17/CanyonGG/assets/59912150/a84c6d73-bcbb-4ff3-b5a9-dff1f0652bd9 width="300"></td>
<td><img src=https://github.com/SEO-J17/CanyonGG/assets/59912150/a1effa55-1910-42d8-b87a-e6463b22464e width="300"></td>
<td><img src=https://github.com/SEO-J17/CanyonGG/assets/59912150/005e0fec-55dd-40a8-bb2b-29412f839571 width="300"></td>
</tr>
</table>

> **전적 상세 화면**
<table>
<tr>
<td>전적 상세 화면</td>
<td>상세 화면 유저 검색</td>
</tr>
<tr>
<td><img src=https://github.com/SEO-J17/CanyonGG/assets/59912150/1a91a336-3475-48ea-baa7-4de1b8172206 width="300"></td>
<td><img src=https://github.com/SEO-J17/CanyonGG/assets/59912150/4e745a69-9a19-4c2c-8fc0-21a4d2838b67 width="300"></td>
</tr>
</table>

> **테마 설정**
<table>
<tr>
<td>테마 설정 기능</td>
</tr>
<tr>
<td><img src=https://github.com/SEO-J17/CanyonGG/assets/59912150/402798ee-1be8-4d4d-909f-f2e95fc2984d width="300"></td>
</tr>
</table>

# 시연
https://github.com/SEO-J17/CanyonGG/assets/59912150/cfd29350-69c9-4abc-b1da-a532be797f4e

