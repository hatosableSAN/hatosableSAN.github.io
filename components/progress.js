function renderProgressInfo(targetSelector, data = {}) {
    const defaultData = {
        storyProgress: data.storyProgress || '第1章まで進行',
        playerLevel: data.playerLevel || 'Lv. 1',
        favoriteCharacter: data.favoriteCharacter || 'まだ'
    };

    const progressHTML = `
        <h3>現在の進捗</h3>
        <div class="progress-info">
            <div class="progress-item">
                <strong>ストーリー進捗:</strong>
                <p>${defaultData.storyProgress}</p>
            </div>
            <div class="progress-item">
                <strong>ユニオンレベル:</strong>
                <p>${defaultData.playerLevel}</p>
            </div>
            <div class="progress-item">
                <strong>推しキャラ:</strong>
                <p>${defaultData.favoriteCharacter}</p>
            </div>
        </div>
    `;

    document.querySelector(targetSelector).innerHTML = progressHTML;
}

// 記事ごとの進捗情報を定義
const postProgressData = {
    '2026-01-19': {
        storyProgress: 'チュートリアルまで',
        playerLevel: 'Lv. 1',
        favoriteCharacter: 'まだ'
    },
    '2026-01-21': {
        storyProgress: '第1章 第1幕',
        playerLevel: 'Lv. 5',
        favoriteCharacter: '第1幕ラストの謎の女'
    }
};

// ページに応じた進捗情報を自動表示
function initializePostProgress() {
    // 記事の日付を取得
    const dateElement = document.querySelector('.date');
    if (!dateElement) return;
    
    const postDate = dateElement.textContent.trim();
    const progressData = postProgressData[postDate] || {};
    
    // 進捗情報の表示スペースがあれば表示
    const progressContainer = document.getElementById('progress-container');
    if (progressContainer) {
        renderProgressInfo('#progress-container', progressData);
    }
}

// ページ読み込み時に実行
document.addEventListener('DOMContentLoaded', initializePostProgress);
