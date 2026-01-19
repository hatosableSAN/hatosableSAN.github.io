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
                <strong>プレイヤーレベル:</strong>
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
