<!DOCTYPE html>
<html lang="en">
<head>
    <title>NFL FUN</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet" media="screen">
    <script src="bootstrap-3.3.7/js/jquery.min.js"></script>
    <script src="bootstrap-3.3.7/js/bootstrap.min.js"></script>

    <style>
        .headerStyle {
            margin-top: 10px;
            margin-bottom: 10px;
            font-size: 21px;
            font-weight: bold;
            color: #00394d;
            background-color: #ccf2ff;
            border: 1px solid #00394d;
            border-radius: 3px;
            text-align: center;
        }
    </style>

    <script>

        function submit() {
            alert("You changes have been submitted...")
            document.forms[0].submit;
        }

        function addTwoNumber(str1, str2, str3){
            var a = document.getElementById(str1).value;
            var b = document.getElementById(str2).value;

            var x = Number(a) + Number(b);
            //document.getElementById(str3).innerHTML = "<b style='color: #e6ffff; font-size: 15px;'>Total Score: " + x + "</b>";
            document.getElementById(str3).value = x;
        }

    </script>
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container">
    <div class="row bg-primary well-sm">
        <ul>
            <li>
                <h5>Total 16 games</h5>
            </li>
            <li>
                <h5>
                    Pool: Top 3 who select highest number of winning teams and who's total score of the 2 teams equals or very close to the actual score will be the winners (50%, 30%, 20%).
                </h5>
            </li>
            <li>
                <h5>Other conditions...</h5>
            </li>
        </ul>
    </div>

    <br><br>
    <div class="row">
        <form action='addScore' method="post">
            <div class="panel panel-primary">
                <div class="panel-heading panel-title">
                    <div class="row">
                        <div class="col-lg-10">
                            <label>Your name goes here...</label>
                        </div>
                        <div class="col-lg-2">
                            <button type="button" onclick="submit();" class="btn btn-info">Submit</button>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <table class="table table-responsive">
                        <tr class="bg-info" style="color: #ffffff;">
                            <th width="4%">1</th>
                            <th width="24%">SEP 08 AT 06.30PM MST</th>
                            <th width="24%">
                                <label>
                                    Broncos
                                </label>
                                <input type="text" size="2" maxlength="2" id="team1Score" onchange="addTwoNumber('team1Score', 'team2Score', 'total1Score')" style="color: #000000;">&nbsp;&nbsp;&nbsp;<input type="radio" name="optRadio1">
                            </th>
                            <th width="24%">
                                <input type="radio" name="optRadio1">&nbsp;&nbsp;&nbsp;<input type="text" size="2" maxlength="2" id="team2Score" name="team2Score" onchange="addTwoNumber('team1Score', 'team2Score', 'total1Score')"  style="color: #000000;">
                                <label>
                                    Panthers
                                </label>
                            </th>
                            <th width="24%">
                                Total:&nbsp;<input type="text" size="3" maxlength="3" id="total1Score" style="color: #000000;">
                            </th>
                        </tr>

                        <tr class="bg-success">
                            <th width="4%">2</th>
                            <th width="24%">SEP 18 AT 02.25PM MST</th>
                            <th width="24%">
                                <label>
                                    Broncos
                                </label>
                                <input type="text" size="2" maxlength="2" id="team3Score" onchange="addTwoNumber('team3Score', 'team4Score', 'total2Score')" style="color: #000000;">&nbsp;&nbsp;&nbsp;<input type="radio" name="optRadio2">
                            </th>
                            <th width="24%">
                                <input type="radio" name="optRadio2">&nbsp;&nbsp;&nbsp;<input type="text" size="2" maxlength="2" id="team4Score" onchange="addTwoNumber('team3Score', 'team4Score', 'total2Score')"  style="color: #000000;">
                                <label>
                                    Colts
                                </label>
                            </th>
                            <th width="24%">
                                Total:&nbsp;<input type="text" size="3" maxlength="3" id="total2Score" style="color: #000000;">
                            </th>
                        </tr>

                        <tr class="bg-info" style="color: #ffffff;">
                            <th width="4%">3</th>
                            <th width="24%">SEP 25 AT 11.00AM MST</th>
                            <th width="24%">
                                <label>
                                    Broncos
                                </label>
                                <input type="text" size="2" maxlength="2" id="team5Score" onchange="addTwoNumber('team5Score', 'team6Score', 'total3Score')" style="color: #000000;">&nbsp;&nbsp;&nbsp;<input type="radio" name="optRadio3">
                            </th>
                            <th width="24%">
                                <input type="radio" name="optRadio3">&nbsp;&nbsp;&nbsp;<input type="text" size="2" maxlength="2" id="team6Score" onchange="addTwoNumber('team5Score', 'team6Score', 'total3Score')"  style="color: #000000;">
                                <label>
                                    Bengals
                                </label>
                            </th>
                            <th width="24%">
                                Total:&nbsp;<input type="text" size="3" maxlength="3" id="total3Score" style="color: #000000;">
                            </th>
                        </tr>

                        <tr class="bg-success">
                            <th width="4%">4</th>
                            <th width="24%">OCT 02 AT 02.05PM MST</th>
                            <th width="24%">
                                <label>
                                    Broncos
                                </label>
                                <input type="text" size="2" maxlength="2" id="team7Score" onchange="addTwoNumber('team17core', 'team8Score', 'total4Score')" style="color: #000000;">&nbsp;&nbsp;&nbsp;<input type="radio" name="optRadio4">
                            </th>
                            <th width="24%">
                                <input type="radio" name="optRadio4">&nbsp;&nbsp;&nbsp;<input type="text" size="2" maxlength="2" id="team8Score" onchange="addTwoNumber('team7Score', 'team8Score', 'total4Score')"  style="color: #000000;">
                                <label>
                                    Buccaneers
                                </label>
                            </th>
                            <th width="24%">
                                Total:&nbsp;<input type="text" size="3" maxlength="3" id="total4Score" style="color: #000000;">
                            </th>
                        </tr>
                        <tr class="bg-info" style="color: #ffffff;">
                            <th width="4%">5</th>
                            <th width="24%">OCT 09 AT 02.05PM MST</th>
                            <th width="24%">
                                <label>
                                    Broncos
                                </label>
                                <input type="text" size="2" maxlength="2" id="team9Score" onchange="addTwoNumber('team9Score', 'team10Score', 'total4Score')" style="color: #000000;">&nbsp;&nbsp;&nbsp;<input type="radio" name="optRadio5">
                            </th>
                            <th width="24%">
                                <input type="radio" name="optRadio5">&nbsp;&nbsp;&nbsp;<input type="text" size="2" maxlength="2" id="team10Score" onchange="addTwoNumber('team9Score', 'team10Score', 'total5Score')"  style="color: #000000;">
                                <label>
                                    Falcons
                                </label>
                            </th>
                            <th width="24%">
                                Total:&nbsp;<input type="text" size="3" maxlength="3" id="total5Score" style="color: #000000;">
                            </th>
                        </tr>
                        <tr class="bg-success">
                            <th width="4%">6</th>
                            <th width="24%">OCT 13 AT 06.25PM MST</th>
                            <th width="24%">
                                <label>
                                    Broncos
                                </label>
                                <input type="text" size="2" maxlength="2" id="team11Score" onchange="addTwoNumber('team11Score', 'team12Score', 'total6Score')" style="color: #000000;">&nbsp;&nbsp;&nbsp;<input type="radio" name="optRadio6">
                            </th>
                            <th width="24%">
                                <input type="radio" name="optRadio6">&nbsp;&nbsp;&nbsp;<input type="text" size="2" maxlength="2" id="team12Score" onchange="addTwoNumber('team11Score', 'team12Score', 'total6Score')"  style="color: #000000;">
                                <label>
                                    Chargers
                                </label>
                            </th>
                            <th width="24%">
                                Total:&nbsp;<input type="text" size="3" maxlength="3" id="total6Score" style="color: #000000;">
                            </th>
                        </tr>
                        <tr class="bg-info" style="color: #ffffff;">
                            <th width="4%">7</th>
                            <th width="24%">OCT 24 AT 06.30PM MST</th>
                            <th width="24%">
                                <label>
                                    Broncos
                                </label>
                                <input type="text" size="2" maxlength="2" id="team13Score" onchange="addTwoNumber()" style="color: #000000;">&nbsp;&nbsp;&nbsp;<input type="radio" name="optRadio7">
                            </th>
                            <th width="24%">
                                <input type="radio" name="optRadio7">&nbsp;&nbsp;&nbsp;<input type="text" size="2" maxlength="2" id="team14Score" onchange="addTwoNumber()"  style="color: #000000;">
                                <label>
                                    Texans
                                </label>
                            </th>
                            <th width="24%">
                                Total:&nbsp;<input type="text" size="3" maxlength="3" id="total7Score" style="color: #000000;">
                            </th>
                        </tr>
                        <tr class="bg-success">
                            <th width="4%">8</th>
                            <th width="24%">OCT 30 AT 02.05PM MST</th>
                            <th width="24%">
                                <label>
                                    Broncos
                                </label>
                                <input type="text" size="2" maxlength="2" id="team15Score" onchange="addTwoNumber()" style="color: #000000;">&nbsp;&nbsp;&nbsp;<input type="radio" name="optRadio8">
                            </th>
                            <th width="24%">
                                <input type="radio" name="optRadio8">&nbsp;&nbsp;&nbsp;<input type="text" size="2" maxlength="2" id="team16Score" onchange="addTwoNumber()"  style="color: #000000;">
                                <label>
                                    Chargers
                                </label>
                            </th>
                            <th width="24%">
                                Total:&nbsp;<input type="text" size="3" maxlength="3" id="total8Score" style="color: #000000;">
                            </th>
                        </tr>

                        <tr class="bg-info" style="color: #ffffff;">
                            <th width="4%">9</th>
                            <th width="24%">NOV 06 AT 06.30PM MST</th>
                            <th width="24%">
                                <label>
                                    Broncos
                                </label>
                                <input type="text" size="2" maxlength="2" id="team17Score" onchange="addTwoNumber()" style="color: #000000;">&nbsp;&nbsp;&nbsp;<input type="radio" name="optRadio9">
                            </th>
                            <th width="24%">
                                <input type="radio" name="optRadio9">&nbsp;&nbsp;&nbsp;<input type="text" size="2" maxlength="2" id="team18Score" onchange="addTwoNumber()"  style="color: #000000;">
                                <label>
                                    Raiders
                                </label>
                            </th>
                            <th width="24%">
                                Total:&nbsp;<input type="text" size="3" maxlength="3" id="total9Score" style="color: #000000;">
                            </th>
                        </tr>

                        <tr class="bg-success">
                            <th width="4%">10</th>
                            <th width="24%">NOV 13 AT 11.00PM MST</th>
                            <th width="24%">
                                <label>
                                    Broncos
                                </label>
                                <input type="text" size="2" maxlength="2" id="team19Score" onchange="addTwoNumber()" style="color: #000000;">&nbsp;&nbsp;&nbsp;<input type="radio" name="optRadio10">
                            </th>
                            <th width="24%">
                                <input type="radio" name="optRadio10">&nbsp;&nbsp;&nbsp;<input type="text" size="2" maxlength="2" id="team20Score" onchange="addTwoNumber()"  style="color: #000000;">
                                <label>
                                    Saints
                                </label>
                            </th>
                            <th width="24%">
                                Total:&nbsp;<input type="text" size="3" maxlength="3" id="total10Score" style="color: #000000;">
                            </th>
                        </tr>

                        <tr class="bg-info" style="color: #ffffff;">
                            <th width="4%">11</th>
                            <th width="24%">BYE WEEK</th>
                            <th width="24%">

                            </th>
                            <th width="24%"></th>
                            <th width="24%">

                            </th>
                        </tr>
                        <tr class="bg-success">
                            <th width="4%">12</th>
                            <th width="24%">NOV 27 AT 02.25PM MST</th>
                            <th width="24%">
                                <label>
                                    Broncos
                                </label>
                                <input type="text" size="2" maxlength="2" id="team21Score" onchange="addTwoNumber()" style="color: #000000;">&nbsp;&nbsp;&nbsp;<input type="radio" name="optRadio11">
                            </th>
                            <th width="24%">
                                <input type="radio" name="optRadio11">&nbsp;&nbsp;&nbsp;<input type="text" size="2" maxlength="2" id="team22Score" onchange="addTwoNumber()"  style="color: #000000;">
                                <label>
                                    Chiefs
                                </label>
                            </th>
                            <th width="24%">
                                Total:&nbsp;<input type="text" size="3" maxlength="3" id="total11Score" style="color: #000000;">
                            </th>
                        </tr>
                        <tr class="bg-info" style="color: #ffffff;">
                            <th width="4%">13</th>
                            <th width="24%">DEC 04 AT 11.00AM MST</th>
                            <th width="24%">
                                <label>
                                    Broncos
                                </label>
                                <input type="text" size="2" maxlength="2" id="team23Score" onchange="addTwoNumber()" style="color: #000000;">&nbsp;&nbsp;&nbsp;<input type="radio" name="optRadio12">
                            </th>
                            <th width="24%">
                                <input type="radio" name="optRadio12">&nbsp;&nbsp;&nbsp;<input type="text" size="2" maxlength="2" id="team24Score" onchange="addTwoNumber()"  style="color: #000000;">
                                <label>
                                    Jaguars
                                </label>
                            </th>
                            <th width="24%">
                                Total:&nbsp;<input type="text" size="3" maxlength="3" id="total12Score" style="color: #000000;">
                            </th>
                        </tr>
                        <tr class="bg-success">
                            <th width="4%">14</th>
                            <th width="24%">DEC 11 AT 11.00AM MST</th>
                            <th width="24%">
                                <label>
                                    Broncos
                                </label>
                                <input type="text" size="2" maxlength="2" id="team25Score" onchange="addTwoNumber()" style="color: #000000;">&nbsp;&nbsp;&nbsp;<input type="radio" name="optRadio13">
                            </th>
                            <th width="24%">
                                <input type="radio" name="optRadio13">&nbsp;&nbsp;&nbsp;<input type="text" size="2" maxlength="2" id="team26Score" onchange="addTwoNumber()"  style="color: #000000;">
                                <label>
                                    Titans
                                </label>
                            </th>
                            <th width="24%">
                                Total:&nbsp;<input type="text" size="3" maxlength="3" id="total13Score" style="color: #000000;">
                            </th>
                        </tr>
                        <tr class="bg-info" style="color: #ffffff;">
                            <th width="4%">15</th>
                            <th width="24%">DEC 18 AT 02.25PM MST</th>
                            <th width="24%">
                                <label>
                                    Broncos
                                </label>
                                <input type="text" size="2" maxlength="2" id="team27Score" onchange="addTwoNumber()" style="color: #000000;">&nbsp;&nbsp;&nbsp;<input type="radio" name="optRadio14">
                            </th>
                            <th width="24%">
                                <input type="radio" name="optRadio14">&nbsp;&nbsp;&nbsp;<input type="text" size="2" maxlength="2" id="team28Score" onchange="addTwoNumber()"  style="color: #000000;">
                                <label>
                                    Patriots
                                </label>
                            </th>
                            <th width="24%">
                                Total:&nbsp;<input type="text" size="3" maxlength="3" id="total14Score" style="color: #000000;">
                            </th>
                        </tr>
                        <tr class="bg-success">
                            <th width="4%">16</th>
                            <th width="24%">DEC 25 AT 06.30PM MST</th>
                            <th width="24%">
                                <label>
                                    Broncos
                                </label>
                                <input type="text" size="2" maxlength="2" id="team29Score" onchange="addTwoNumber()" style="color: #000000;">&nbsp;&nbsp;&nbsp;<input type="radio" name="optRadio15">
                            </th>
                            <th width="24%">
                                <input type="radio" name="optRadio15">&nbsp;&nbsp;&nbsp;<input type="text" size="2" maxlength="2" id="team30Score" onchange="addTwoNumber()"  style="color: #000000;">
                                <label>
                                    Chiefs
                                </label>
                            </th>
                            <th width="24%">
                                Total:&nbsp;<input type="text" size="3" maxlength="3" id="total15Score" style="color: #000000;">
                            </th>
                        </tr>

                        <tr class="bg-info" style="color: #ffffff;">
                            <th width="4%">17</th>
                            <th width="24%">JAN 01 AT 02.25PM MST</th>
                            <th width="24%">
                                <label>
                                    Broncos
                                </label>
                                <input type="text" size="2" maxlength="2" id="team31Score" onchange="addTwoNumber()" style="color: #000000;">&nbsp;&nbsp;&nbsp;<input type="radio" name="optRadio16">
                            </th>
                            <th width="24%">
                                <input type="radio" name="optRadio16">&nbsp;&nbsp;&nbsp;<input type="text" size="2" maxlength="2" id="team32Score" name="team32Score" onchange="addTwoNumber()"  style="color: #000000;">
                                <label>
                                    Raiders
                                </label>
                            </th>
                            <th width="24%">
                                Total:&nbsp;<input type="text" size="3" maxlength="3" id="total16Score" style="color: #000000;">
                            </th>
                        </tr>
                    </table>
                </div>
            </div>
        </form>
    </div>

</div>

</body>
</html>