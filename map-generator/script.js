let currentTile = null;
let mapCanvas = document.getElementById('map-canvas');
let ctx = mapCanvas.getContext('2d');

let tileSize = 16;
let mapWidth = 30;
let mapHeight = 30;
let isDrawing = false;

let mapData = [];

let tileImages = {};

const tileColorMap = {
    'TOP_GRASS_1': 0x1A2B3C,
    'TOP_GRASS_2': 0x2B3C4D,
    'TOP_GRASS_3': 0x3C4D5E,
    'TOP_GRASS_4': 0x4D5E6F,
    'DIRT_TOP_RIGHT': 0x5E6F70,
    'DIRT_TOP_CENTER': 0x6F7071,
    'DIRT_TOP_LEFT': 0x707172,
    'DIRT_CENTER_RIGHT': 0x818283,
    'DIRT_CENTER': 0x929394,
    'DIRT_CENTER_LEFT': 0xA1B2C3,
    'DIRT_BOTTOM_RIGHT': 0xB1C2D3,
    'DIRT_BOTTOM_CENTER': 0xC1D2E3,
    'DIRT_BOTTOM_LEFT': 0xD1E2F3,
    'HILL_TOP_LEFT': 0xE1F2F3,
    'HILL_TOP_RIGHT': 0xF1F2F3,
    'HILL_BOTTOM_LEFT': 0x101112,
    'HILL_BOTTOM_RIGHT': 0x111213,
    'RAVINE_TOP_LEFT': 0x121314,
    'RAVINE_TOP_CENTER': 0x131415,
    'RAVINE_TOP_RIGHT': 0x141516,
    'RAVINE_CENTER_LEFT': 0x151617,
    'RAVINE_CENTER_RIGHT': 0x161718,
    'RAVINE_BOTTOM_LEFT': 0x171819,
    'RAVINE_BOTTOM_CENTER': 0x18191A,
    'RAVINE_BOTTOM_RIGHT': 0x191A1B,
    'DIRT_WALL': 0x202122,
    'SAND': 0x212223,
    'WATER': 0x222324,
    'WATER_RAVINE_TOP_LEFT': 0x232425,
    'WATER_RAVINE_TOP_CENTER': 0x242526,
    'WATER_RAVINE_TOP_RIGHT': 0x252627,
    'WATER_RAVINE_CENTER_LEFT': 0x262728,
    'WATER_RAVINE_CENTER_RIGHT': 0x272829,
    'WATER_RAVINE_BOTTOM_LEFT': 0x28292A,
    'WATER_RAVINE_BOTTOM_CENTER': 0x292A2B,
    'WATER_RAVINE_BOTTOM_RIGHT': 0x2A2B2C,
    'DIRT_ROAD_TOP_LEFT': 0x2B2C2D,
    'DIRT_ROAD_TOP_CENTER': 0x2C2D2E,
    'DIRT_ROAD_TOP_RIGHT': 0x2D2E2F,
    'DIRT_ROAD_CENTER_LEFT': 0x2E2F30,
    'DIRT_ROAD_CENTER': 0x2F3031,
    'DIRT_ROAD_CENTER_RIGHT': 0x303132,
    'DIRT_ROAD_BOTTOM_LEFT': 0x313233,
    'DIRT_ROAD_BOTTOM_CENTER': 0x323334,
    'DIRT_ROAD_BOTTOM_RIGHT': 0x333435,
};

function loadTileImages() {
    const tileNames = [
        "TOP_GRASS_1", "TOP_GRASS_2", "TOP_GRASS_3", "TOP_GRASS_4",
        "DIRT_TOP_RIGHT", "DIRT_TOP_CENTER", "DIRT_TOP_LEFT", "DIRT_CENTER_RIGHT",
        "DIRT_CENTER", "DIRT_CENTER_LEFT", "DIRT_BOTTOM_RIGHT", "DIRT_BOTTOM_CENTER",
        "DIRT_BOTTOM_LEFT", "HILL_TOP_LEFT", "HILL_TOP_RIGHT", "HILL_BOTTOM_LEFT",
        "HILL_BOTTOM_RIGHT", "RAVINE_TOP_LEFT", "RAVINE_TOP_CENTER", "RAVINE_TOP_RIGHT",
        "RAVINE_CENTER_LEFT", "RAVINE_CENTER_RIGHT", "RAVINE_BOTTOM_LEFT", "RAVINE_BOTTOM_CENTER",
        "RAVINE_BOTTOM_RIGHT", "DIRT_WALL", "SAND", "WATER", "WATER_RAVINE_TOP_LEFT",
        "WATER_RAVINE_TOP_CENTER", "WATER_RAVINE_TOP_RIGHT", "WATER_RAVINE_CENTER_LEFT",
        "WATER_RAVINE_CENTER_RIGHT", "WATER_RAVINE_BOTTOM_LEFT", "WATER_RAVINE_BOTTOM_CENTER",
        "WATER_RAVINE_BOTTOM_RIGHT", "DIRT_ROAD_TOP_LEFT", "DIRT_ROAD_TOP_CENTER",
        "DIRT_ROAD_TOP_RIGHT", "DIRT_ROAD_CENTER_LEFT", "DIRT_ROAD_CENTER", "DIRT_ROAD_CENTER_RIGHT",
        "DIRT_ROAD_BOTTOM_LEFT", "DIRT_ROAD_BOTTOM_CENTER", "DIRT_ROAD_BOTTOM_RIGHT"
    ];

    tileNames.forEach(tile => {
        let img = new Image();
        img.src = `./tiles_image/${tile}.png`;
        tileImages[tile] = img;

        img.onload = function() {
            let tileImageElement = document.createElement('img');
            tileImageElement.src = img.src;
            tileImageElement.alt = tile;
            tileImageElement.onclick = function() {
                setTile(tile);
                updateSelectedTile(tileImageElement);
            };
            document.getElementById('tiles-selection').appendChild(tileImageElement);
        };
    });
}

function setTile(tile) {
    currentTile = tile;
}

function updateSelectedTile(selectedElement) {
    let allTiles = document.querySelectorAll('.tiles-selection img');
    allTiles.forEach(tile => {
        tile.classList.remove('selected');
    });
    selectedElement.classList.add('selected');
}

function createMap() {
    let canvasWidth = mapWidth * tileSize;
    let canvasHeight = mapHeight * tileSize;

    mapCanvas.width = canvasWidth;
    mapCanvas.height = canvasHeight;

    ctx.fillStyle = "#DDDDDD";
    ctx.fillRect(0, 0, canvasWidth, canvasHeight);

    drawTiles();
}

function drawTiles() {
    for (let x = 0; x < mapWidth * tileSize; x += tileSize) {
        for (let y = 0; y < mapHeight * tileSize; y += tileSize) {
            ctx.fillStyle = "#DDDDDD";
            ctx.fillRect(x, y, tileSize, tileSize);
        }
    }

    for (let i = 0; i < mapData.length; i++) {
        const tile = mapData[i];
        ctx.drawImage(tileImages[tile.type], tile.x, tile.y, tileSize, tileSize);
    }
}

function draw(x, y) {
    const tileX = Math.floor(x / tileSize) * tileSize;
    const tileY = Math.floor(y / tileSize) * tileSize;

    const existingTileIndex = mapData.findIndex(tile => tile.x === tileX && tile.y === tileY);
    
    if (existingTileIndex !== -1) {
        mapData.splice(existingTileIndex, 1);
    }

    mapData.push({ x: tileX, y: tileY, type: currentTile });

    createMap();
}

function removeTile(x, y) {
    const tileX = Math.floor(x / tileSize) * tileSize;
    const tileY = Math.floor(y / tileSize) * tileSize;

    mapData = mapData.filter(tile => tile.x !== tileX || tile.y !== tileY);

    ctx.fillStyle = "#DDDDDD";
    ctx.fillRect(tileX, tileY, tileSize, tileSize);
}

mapCanvas.addEventListener('mousedown', function(event) {
    if (event.button === 0) {
        if (!currentTile) {
            alert('Selecione um tile antes de desenhar!');
            return;
        }
        isDrawing = true;
        draw(event.offsetX, event.offsetY);
    }
});

mapCanvas.addEventListener('mouseup', function() {
    isDrawing = false;
});

mapCanvas.addEventListener('mousemove', function(event) {
    if (isDrawing) {
        draw(event.offsetX, event.offsetY);
    }

    const tileX = Math.floor(event.offsetX / tileSize) * tileSize;
    const tileY = Math.floor(event.offsetY / tileSize) * tileSize;

    const tileAtPosition = mapData.find(tile => tile.x === tileX && tile.y === tileY);

    if (tileAtPosition) {
        document.getElementById('tile-position').textContent = `(${tileX / tileSize}, ${tileY / tileSize})`;
        document.getElementById('tile-image').textContent = tileAtPosition.type;
    } else {
        document.getElementById('tile-position').textContent = `--, --`;
        document.getElementById('tile-image').textContent = `Nenhum`;
    }
});

mapCanvas.addEventListener('contextmenu', function(event) {
    event.preventDefault();
    removeTile(event.offsetX, event.offsetY);
});

document.getElementById('export-btn').addEventListener('click', function() {
    let exportCanvas = document.createElement('canvas');
    let exportCtx = exportCanvas.getContext('2d');

    exportCanvas.width = mapWidth;
    exportCanvas.height = mapHeight;

    exportCtx.fillStyle = "#DDDDDD";
    exportCtx.fillRect(0, 0, exportCanvas.width, exportCanvas.height);

    for (let x = 0; x < mapWidth; x++) {
        for (let y = 0; y < mapHeight; y++) {
            const tile = mapData.find(tile => tile.x === x * tileSize && tile.y === y * tileSize);

            if (tile) {
                const tileColor = tileColorMap[tile.type];
                exportCtx.fillStyle = `#${tileColor.toString(16).padStart(6, '0')}`;
                exportCtx.fillRect(x, y, 1, 1);
            }
        }
    }

    let newWindow = window.open('', '_blank');
    newWindow.document.write('<html><body><h2>Mapa Exportado</h2></body></html>');

    let img = new Image();
    img.src = exportCanvas.toDataURL();

    img.onload = function() {
        img.width = 200;
        img.height = 200;
        newWindow.document.body.appendChild(img);
    };
});

document.getElementById('create-map-btn').addEventListener('click', function() {
    mapWidth = document.getElementById('map-width').value;
    mapHeight = document.getElementById('map-height').value;
    createMap();
});

loadTileImages();
createMap();
