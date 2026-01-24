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

// ページの時系列順序を定義
const postPages = [
    { date: '2026-01-19', title: 'チュートリアルまで' },
    { date: '2026-01-21', title: '1章 その1' }
];

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
    
    // ページナビゲーション（前後のリンク）を表示
    renderPageNavigation(postDate);
}

// ページナビゲーション（前後のリンク）を生成
function renderPageNavigation(currentDate) {
    const currentIndex = postPages.findIndex(page => page.date === currentDate);
    if (currentIndex === -1) return;
    
    const prevPage = currentIndex > 0 ? postPages[currentIndex - 1] : null;
    const nextPage = currentIndex < postPages.length - 1 ? postPages[currentIndex + 1] : null;
    
    const navHTML = `
        <div class="post-navigation">
            ${prevPage ? `<a href="../posts/${prevPage.date}.html" class="nav-prev">← 前の記事: ${prevPage.title}</a>` : '<span></span>'}
            ${nextPage ? `<a href="../posts/${nextPage.date}.html" class="nav-next">次の記事: ${nextPage.title} →</a>` : '<span></span>'}
        </div>
    `;
    
    const backLink = document.querySelector('.back-link');
    if (backLink) {
        backLink.insertAdjacentHTML('beforebegin', navHTML);
    }
}

// ページ読み込み時に実行
document.addEventListener('DOMContentLoaded', initializePostProgress);
