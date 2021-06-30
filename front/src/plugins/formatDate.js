export default function (dateString) {
  const nowDate = new Date(dateString);
  let formatAddZeroLessTen = v => {
    let result;
    if (v < 10) result = '0' + v;
    else result = v;
    return result;
  };
  return nowDate.getFullYear() + "/" + formatAddZeroLessTen(nowDate.getMonth()+1) +
    "/" + formatAddZeroLessTen(nowDate.getDate()) + " " +
    formatAddZeroLessTen(nowDate.getHours()) + ":" +
    formatAddZeroLessTen(nowDate.getMinutes()) + ":" + formatAddZeroLessTen(nowDate.getSeconds());
};
