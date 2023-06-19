function getDistLabels() {
    const items = $("#speeds").text().replace("[", "").replace("]", "").split(', ');
    return items;
}

function getDistMaxwell() {
    const items = $("#distMaxwell").text().replace("[", "").replace("]", "").split(', ');
    return items;
}

function getDistGinzburg() {
    const items = $("#distGinzburg").text().replace("[", "").replace("]", "").split(', ');
    return items;
}

function getConcLabels() {
    const items = $("#radiuces").text().replace("[", "").replace("]", "").split(', ');
    return items;
}

function getConcMaxwell() {
    const items = $("#concMaxwell").text().replace("[", "").replace("]", "").split(', ');
    return items;
}

function getConcGinzburg() {
    const items = $("#concGinzburg").text().replace("[", "").replace("]", "").split(', ');
    return items;
}