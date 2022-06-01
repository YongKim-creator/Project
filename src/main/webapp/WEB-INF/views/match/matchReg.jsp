<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <%@include file="../header.jsp"%>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#matchLocM").change(function(){

                // 지역상세 콤보값 배열 선언
                let seoul = ["마포", "강서", "용산"];
                let gyeonggi = ["파주", "고양", "김포"];

                // 지역 콤보값
                let matchLocM = $("#matchLocM").val();

                // 지역상세 콤보에 설정할 배열 변수
                let changeItem;

                // 지역 값이 '서울'인 경우
                if (matchLocM == "서울") {
                    changeItem = seoul;
                }
                // 지역 값이 '경기'인 경우
                else if (matchLocM == "경기") {
                    changeItem = gyeonggi;
                }
                // 지역 값이 '지역선택'인 경우
                else {
                    changeItem = ["지역상세선택"];
                }

                // 지역상세 콤보값 초기화
                $("#matchLocD").empty();

                // changeItem.length 배열의 갯수만큼 지역상세 콤보에 추가 (한번에 한개씩 추가)
                for(let i=0; i<changeItem.length; i++) {
                    // 옵션값 수동 생성
                    let option = "<option value='" + changeItem[i] + "'>" + changeItem[i] + "</option>";

                    // 지역상세 콤보값 추가
                    $("#matchLocD").append(option);
                }
            })
        })
    </script>
</head>
<body>
<%@include file="../headerMenu.jsp"%>
    <h1>매치생성</h1>
    <form id="matchReg" action="/matchReg" method="post">
        <!-- 경기지역 -->
        <div class="form-group">
            <label for="matchLocM">경기지역</label>
            <select id="matchLocM" class="form-control" name="matchLocM">
                <option value="">지역선택</option>
                <option value="서울">서울</option>
                <option value="경기">경기</option>
            </select>
        </div>
        <!-- 상세지역 -->
        <div class="form-group">
            <label for="matchLocD">상세지역</label>
            <select id="matchLocD" class="form-control" name="matchLocD">
                <option value="">지역상세선택</option>
            </select>
        </div>
        <!-- 구장이름-->
        <div class="form-group">
            <label for="matchGmName">구장 이름</label>
            <input type="text" class="form-control" id="matchGmName" name="matchGmName" value="테스트구장이름">
        </div>
        <!-- 구장장소 -->
        <div class="form-group">
            <label for="matchGmAddr">구장 장소</label>
            <input type="text" class="form-control" id="matchGmAddr" name="matchGmAddr" value="테스트구장장소">
        </div>
        <!-- 경기일자 -->
        <div class="form-group">
            <label for="matchDate">경기일자</label>
            <input type="date" class="form-control" id="matchDate" name="matchDate">
        </div>
        <!-- 경기시간 -->
        <div class="form-group">
            <label for="matchTime">경기 시간</label>
            <select id="matchTime" class="form-control" name="matchTime">
                <option value="15:00~17:00">15:00~17:00</option>
                <option value="17:00~19:00">17:00~19:00</option>
                <option value="19:00~21:00">19:00~21:00</option>
            </select>
        </div>
        <!-- 성별 -->
        <div class="form-group">
            <label for="matchGender">성별 구분</label>
            <select id="matchGender" class="form-control" name="matchGender">
                <option value="남자">남자</option>
                <option value="여자">여자</option>
            </select>

            <!--
            <input type="submit" onclick="location.href='/matchReg'" value="매칭시작">
            -->
        </div><br>
    </form>

<%@include file="../footer.jsp"%>
</body>
</html>