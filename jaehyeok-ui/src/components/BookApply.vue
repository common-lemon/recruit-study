<template>
    <div class="container">
        <form >
            <h2>도서 신청</h2>
            <div class="form-group">
                <label >신청부서</label>
                <input placeholder="신청부서" required />
            </div>
            <div class="form-group">
                <label >신청자</label>
                <input placeholder="신청자"  required />
            </div>
            <div class="form-group">
                <label >도서명</label>
                <input placeholder="도서명"  required />
            </div>
            <div class="form-group">
                <label >출판사</label>
                <input placeholder="출판사"  required />
            </div>
            <div class="form-group">
            <label >도서 금액</label>
            <input placeholder="도서금액"  required />
            </div>
            <div class="form-group">
                <label >신청사유</label>
                <textarea id="content"  class="form-control" placeholder="내용을 입력하세요" />
            </div>
            <div class="btn-cover">
                <button type="submit">신청</button>
            </div>
        </form>
    </div>
</template>

<style lang="scss" scoped>
textarea{
    width:350px;
    height:150px;
    resize: vertical;  //세로크기만 조절가능
    background-color: #e3e9ef;
    color: #134775;

}
.container {
    form {
        h2 {
            font-size: 1.5rem;
            margin-bottom: 10px;
            margin-left: 15px;
            font-family: 'Titillium Web';
        }
        .form-group{
            display: flex;
            flex-direction: column;
        }
        label {
            color: #134775;
            margin-bottom: 10px;
        }
        input {
            padding: 10px;
            width: 350px;
            margin-bottom: 15px;
            border: none;
            background-color: #e3e9ef;
            color: #134775;
        }
        .btn-cover{
            text-align: center;
            button {
                margin-top: 15px;
                border: none;
                background: #134775;
                color: #f4f2db;
                font-size: 1.1rem;
                padding: 15px 60px;
                font-weight: bold;
                border-radius: 30px;
                cursor: pointer;
                width: 350px;
            }
        }

        display: flex;
        width: 100%;
        height: 730px;
        background-color: aliceblue;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        color: #134775;
    }
}


</style>